package dao;

import mapper.UserMapper;
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

    private UserMapper userMapper = null;

    public User getUserByLoginName(String loginName){
        userMapper = sqlSessionTemplate.getMapper(UserMapper.class);
        return userMapper.selectUserByLoginName(loginName);
    }

    public User getUserById(int id){
        userMapper = sqlSessionTemplate.getMapper(UserMapper.class);
        return userMapper.selectUserById(id);
    }
}
