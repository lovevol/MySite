package test;

import model.Web;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by lh
 * on 2017/9/6.
 */
public class MainTest {
    public static void main(String[] args) {
        try {
            InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            /*int id = 2;
            User user = sqlSession.selectOne("mapper.UserMapper.selectUserById",id);
            System.out.println(user);*/
            /*Web web = new Web();
            web.setWebUrl("http://mvnrepository.com/");
            web.setDescription("搜索maven仓库中的依赖");
            web.setRemark("不用vpn");
            sqlSession.insert("mapper.ShareMapper.saveWeb",web);*/
            List<Web> webs = sqlSession.selectList("mapper.ShareMapper.selectWeb");
            System.out.println(webs);
            sqlSession.commit();
            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
