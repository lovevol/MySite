package mybatisTypeHandler;

import myenum.Gender;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 性别枚举类的转换映射
 * @author lh
 */
public class MyGenderTypeHandler implements TypeHandler<Gender> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Gender gender, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i,gender.getId());
    }

    @Override
    public Gender getResult(ResultSet resultSet, String s) throws SQLException {
        int id = resultSet.getInt(s);
        return Gender.getGender(id);
    }

    @Override
    public Gender getResult(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt(i);
        return Gender.getGender(id);
    }

    @Override
    public Gender getResult(CallableStatement callableStatement, int i) throws SQLException {
        int id = callableStatement.getInt(i);
        return Gender.getGender(id);
    }
}
