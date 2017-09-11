package dao;

import Utils.MyBatisUtil;
import model.Web;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by lh
 * on 2017/9/11.
 */
@Component
public class WebDAO {
    private SqlSession sqlSession = MyBatisUtil.getSqlSession();

    /**
     * 获取主页的网站分享
     * @return
     */
    public List<Web> getWebForIndex(){
        List<Web> webs = sqlSession.selectList("mapper.ShareMapper.selectWebForIndex");
        sqlSession.commit();
        return webs;
    }

    /**
     * 添加网站
     * @param web
     * @return
     */
    public int addWeb(Web web){
        int id = sqlSession.insert("mapper.ShareMapper.saveWeb",web);
        sqlSession.commit();
        return id;
    }
}
