package mapper;

/**
 * Created by Administrator on 2017/9/13.
 */

import model.User;
import org.springframework.stereotype.Repository;

/**
 * mybatis的Mapper接口
 */
@Repository
public interface UserMapper {
    User selectUserByLoginName(String loginName);
    User selectUserById(int id);
    int save(User user);
    int deleteUserById(int id);
    int updateUserById(User user);
}
