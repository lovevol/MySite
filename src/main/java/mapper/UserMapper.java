package mapper;

/**
 * Created by Administrator on 2017/9/13.
 */

import model.User;

/**
 * mybatis的Mapper接口
 */
public interface UserMapper {
    public User selectUserByLoginName(String loginName);
    public User selectUserById(int id);
    public int save(User user);
    public int deleteUserById(int id);
    public int updateUserById(User user);
}
