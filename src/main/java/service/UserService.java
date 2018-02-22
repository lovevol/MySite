package service;

import PagingPlugin.PageParams;
import dao.UserDAO;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.MD5Util;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * Created by lh
 * on 2017/9/11.
 * @author lh
 */
@Service
public class UserService {
    private Logger logger = Logger.getLogger(this.getClass().getName());
    private final UserDAO userDAO;
    private EmailService emailService;
    @Autowired
    public UserService(UserDAO userDAO,EmailService emailService) {
        this.userDAO = userDAO;
        this.emailService = emailService;
    }

    /**
     * 验证登录信息
     * @param user 用户
     * @return 验证结果
     */
    public boolean validateLogin(User user){
        User userForValidate = userDAO.getUserByLoginName(user.getLoginName());
        user.setPassword(MD5Util.getMD5(user.getPassword()));
        if (userForValidate != null && user.getPassword().equals(userForValidate.getPassword()) &&
                user.getRoleType() == userForValidate.getRoleType()){
            return true;
        }else {
            return false;
        }
    }

    public User getUserByLoginName(String loginName){
        return userDAO.getUserByLoginName(loginName);
    }

    /**
     *保存用户
     * @param user 用户
     * @return 结果
     */
    public int saveUser(User user){
        return userDAO.addUser(user);
    }
    public int registerUser(User user){
        user.setPassword(MD5Util.getMD5(user.getPassword()));
        user.setStatus(0);
        user.setRoleType((byte) 1);
        user.setValidateDate(new Timestamp(System.currentTimeMillis()));
        user.setValidateCode(UUID.randomUUID().toString());
        try {
            emailService.sendEmailForUserValidate(user);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("发送邮件出错"+e.getMessage());
        }
        return userDAO.addUser(user);
    }

    /**
     * 根据验证码激活用户
     * @param user
     * @return
     */
    public boolean updateUserByIdAndValidateCode(User user){
        user = userDAO.getUserById(user.getIdUser());
        Timestamp now = new Timestamp(System.currentTimeMillis());
        if(now.getTime() - user.getValidateDate().getTime() /(60 * 60 * 1000) <= 3){
            return false;
        }else {
            userDAO.updateUserByIdAndValidateCode(user);
            return true;
        }
    }

    /**
     * 批量添加用户
     * @param list
     * @return
     */
    public int addUserByBatch(List<User> list){
        return userDAO.addUserByBatch(list);
    }

    public List<User> getUSer(PageParams pageParams){
        return userDAO.getUSer(pageParams);
    }

    /**
     * 用户激活
     * @param user
     * @return
     */
    public boolean activeUser(User user){
        User dbUSer = userDAO.getUserByLoginName(user.getLoginName());
        Timestamp now = new Timestamp(System.currentTimeMillis());
        if (dbUSer.getValidateCode().equals(user.getValidateCode())){
            if (now.getTime() - dbUSer.getValidateDate().getTime() /(60 * 60 * 1000) <= 3){
                return false;
            }
            updateUserByIdAndValidateCode(dbUSer);
            return true;
        }
        return false;
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    public int updateUser(User user){
        return userDAO.updateUser(user);
    }

    /**
     * 修改密码是发送验证码
     * @param user
     * @return
     * @throws Exception
     */
    public boolean sendValidateCoedForChangePassword(User user) throws Exception {
        User dbUser = userDAO.getUserByLoginName(user.getLoginName());
        if (dbUser != null){
            dbUser.setValidateCode(UUID.randomUUID().toString());
            dbUser.setValidateDate(new Timestamp(System.currentTimeMillis()));
            emailService.sendEmailForChangePassword(dbUser);
            updateUser(dbUser);
            return true;
        }
        return false;
    }

    /**
     * 修改密码
     * @param user
     * @return
     */
    public boolean changePassword(User user){
        User dbUser = userDAO.getUserByLoginName(user.getLoginName());
        if (dbUser != null){
            if (dbUser.getValidateCode().equals(user.getValidateCode())){
                dbUser.setPassword(MD5Util.getMD5(user.getPassword()));
                updateUser(dbUser);
                return true;
            }
        }
        return false;
    }
}
