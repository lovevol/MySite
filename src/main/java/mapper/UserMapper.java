package mapper;

/**
 * Created by Administrator on 2017/9/13.
 */

import PagingPlugin.PageParams;
import model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * mybatis的Mapper接口
 * @author lh
 */
@Repository
public interface UserMapper {
    /**
     * 按照登录名获取用户
     * @param loginName
     * @return
     */
    User selectUserByLoginName(String loginName);

    /**
     * 按照ｉｄ获取用户
     * @param id
     * @return
     */
    User selectUserById(int id);

    /**
     * 保存用户
     * @param user
     * @return
     */
    int save(User user);

    /**
     * 按照ｉｄ删除用户
     * @param id
     * @return
     */
    int deleteUserById(int id);

    /**
     * 更新用户
     * @param user
     * @return
     */
    int updateUserById(User user);

    /**
     * 用户激活
     * 按照ｉｄ和validateCode
     * @param user
     */
    void updateUserByIdAndValidateCode(User user);

    int saveUserByBatch(List<User> userList);
    List<User> getUser(PageParams pageParams);

    int updateUser(User user);
}
