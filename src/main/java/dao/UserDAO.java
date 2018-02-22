package dao;

import PagingPlugin.PageParams;
import mapper.UserMapper;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by lh
 * on 2017/9/11.
 * @author lh
 */
@Component
public class UserDAO {
    @Autowired
    private UserMapper userMapper;

    /**
     * 按照登录名获取用户
     * @param loginName 登录名
     * @return 用户
     */
    public User getUserByLoginName(String loginName){
        return userMapper.selectUserByLoginName(loginName);
    }

    /**
     * 按照id获取用户
     * @param id id
     * @return 用户
     */
    public User getUserById(int id){
        return userMapper.selectUserById(id);
    }

    /**
     * t添加用户
     * @param user 用户
     * @return 结果
     */
    public int addUser(User user){
        return userMapper.save(user);
    }

    /**
     * 用户激活按照激活码更新用户信息
     * @param user 用户
     */
    public void updateUserByIdAndValidateCode(User user){
        userMapper.updateUserByIdAndValidateCode(user);
    }

    public int addUserByBatch(List<User> userList){
        return userMapper.saveUserByBatch(userList);
    }
    public List<User> getUSer(PageParams pageParams){
        return userMapper.getUser(pageParams);
    }

    public int updateUser(User user){
        return userMapper.updateUser(user);
    }
}
