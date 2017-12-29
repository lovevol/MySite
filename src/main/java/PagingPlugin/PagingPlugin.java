package PagingPlugin;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 自定义分页插件
 */
@Intercepts(
        //拦截方法签名
        @Signature(

                type = StatementHandler.class,
                method = "prepare",
                args = {Connection.class,Integer.class}
        )
)
public class PagingPlugin implements Interceptor {
    private Integer defaultPage;
    private Integer defaultPageSize;
    private Boolean defaultUseFlag;
    private Boolean defaultCheckFlag;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = getUnProxyObject(invocation);
        MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);
        String sql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
        if (!checkSelect(sql)) {
            return invocation.proceed();
        }
        BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
        Object parameterObject = boundSql.getParameterObject();
        PageParams pageParams = getPageParams(parameterObject);
        if (pageParams == null) {
            return invocation.proceed();
        }
        Integer pageNum = pageParams.getPage() == null ? this.defaultPage : pageParams.getPage();
        Integer pageSize = pageParams.getPageSize() == null ? this.defaultPageSize : pageParams.getPageSize();
        Boolean userFlag = pageParams.getUseFlag() == null ? this.defaultUseFlag : pageParams.getUseFlag();
        Boolean checkFlag = pageParams.getCheckFlag() == null ? this.defaultCheckFlag : pageParams.getCheckFlag();
        if (!userFlag) {
            return invocation.proceed();
        }
        int total = getTotal(invocation, metaStatementHandler, boundSql);
        setTotalToPageParams(pageParams, total, pageSize);
        checkPage(checkFlag,pageNum,pageParams.getTotalPage());
        return changeSql(invocation,metaStatementHandler,boundSql,pageNum,pageSize);
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {
        String strDefaultPage = properties.getProperty("default.page", "1");
        String strDefaultPageSize = properties.getProperty("default.pageSize", "20");
        String strDefaultUseFlag = properties.getProperty("default.useFlag", "false");
        String strDefaultCheckFlag = properties.getProperty("default.checkFlag", "false");
        this.defaultPage = Integer.parseInt(strDefaultPage);
        this.defaultPageSize = Integer.parseInt(strDefaultPageSize);
        this.defaultUseFlag = Boolean.parseBoolean(strDefaultUseFlag);
        this.defaultCheckFlag = Boolean.parseBoolean(strDefaultCheckFlag);
    }

    private StatementHandler getUnProxyObject(Invocation invocation) {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        Object object = null;
        while (metaObject.hasGetter("h")) {
            object = metaObject.getValue("h");
        }
        if (object == null) {
            return statementHandler;
        }
        return (StatementHandler) object;
    }

    private boolean checkSelect(String sql) {
        String trimSql = sql.trim();
        int index = trimSql.toLowerCase().indexOf("select");
        return index == 0;
    }

    private PageParams getPageParams(Object pageParamsObject) {
        if (pageParamsObject == null) {
            return null;
        }
        PageParams pageParams = null;
        if (pageParamsObject instanceof Map) {
            @SuppressWarnings("unchecked")
            Map<String, Object> paramMap = (Map<String, Object>) pageParamsObject;
            Set<String> keySet = paramMap.keySet();
            for (String key : keySet) {
                Object value = paramMap.get(key);
                if (value instanceof PageParams) {
                    return (PageParams) value;
                }
            }
        } else if (pageParamsObject instanceof PageParams) {
            return (PageParams) pageParamsObject;
        }
        return pageParams;
    }

    private int getTotal(Invocation invocation, MetaObject metaStatmentHandler, BoundSql boundSql) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) metaStatmentHandler.getValue("delegate.mappedStatement");
        Configuration configuration = mappedStatement.getConfiguration();
        String sql = (String) metaStatmentHandler.getValue("delegate.boundSql.sql");
        String countSql = "select count(*) as total from (" + sql + ")$_paging";
        Connection connection = (Connection) invocation.getArgs()[0];
        PreparedStatement preparedStatement = null;
        int total = 0;
        try {
            preparedStatement = connection.prepareStatement(countSql);
            BoundSql countBountSql = new BoundSql(configuration, countSql, boundSql.getParameterMappings(), boundSql.getParameterObject());
            ParameterHandler handler = new DefaultParameterHandler(mappedStatement, boundSql.getParameterObject(), countBountSql);
            handler.setParameters(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                total = resultSet.getInt("total");
            }
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        System.out.println("总条数：" + total);
        return total;
    }

    private void setTotalToPageParams(PageParams pageParams, int total, int pageSize) {
        pageParams.setTotal(total);
        int totalPage = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
        pageParams.setTotalPage(totalPage);
    }
    private void checkPage(Boolean checkFlag,Integer pageNum,Integer pageTotal) throws Throwable{
        if (checkFlag){
            if (pageNum > pageTotal){
                throw new Exception("查询失败，页码大于总页码");
            }
        }
    }
    private Object changeSql(Invocation invocation,MetaObject metaStatementHandler,BoundSql boundSql,int page,int pageSize) throws Throwable{
        String sql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
        String newSql = "Select * from ("+ sql + ")$_paging_table limit ?,?";
        metaStatementHandler.setValue("delegate.boundSql.sql",newSql);
        PreparedStatement ps = (PreparedStatement)invocation.proceed();
        int count = ps.getParameterMetaData().getParameterCount();
        ps.setInt(count - 1,(page - 1)*pageSize);
        ps.setInt(count,pageSize);
        return ps;
    }
}
