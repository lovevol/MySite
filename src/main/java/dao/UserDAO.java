package dao;

import model.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by lh
 * on 2017/9/11.
 */
@Component
public class UserDAO {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public User getUserByLoginName(String loginName){
        User user = sqlSessionTemplate.selectOne("mapper.UserMapper.selectUserByLoginName",loginName);
        return user;
    }
}
