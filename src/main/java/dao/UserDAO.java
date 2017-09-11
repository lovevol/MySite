package dao;

import Utils.MyBatisUtil;
import model.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

/**
 * Created by lh
 * on 2017/9/11.
 */
@Component
public class UserDAO {
    private SqlSession sqlSession = MyBatisUtil.getSqlSession();

    public User getUserByLoginName(String loginName){
        User user = sqlSession.selectOne("mapper.UserMapper.selectUserByLoginName",loginName);
        sqlSession.commit();
        return user;
    }
}
