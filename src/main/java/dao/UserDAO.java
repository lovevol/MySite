package dao;

import mapper.UserMapper;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by lh
 * on 2017/9/11.
 */
@Component
public class UserDAO {
    @Autowired
    private UserMapper userMapper;

    public User getUserByLoginName(String loginName){
        return userMapper.selectUserByLoginName(loginName);
    }

    public User getUserById(int id){
        return userMapper.selectUserById(id);
    }
    public int addUser(User user){
        return userMapper.save(user);
    }
}
