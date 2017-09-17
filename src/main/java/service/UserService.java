package service;

import dao.UserDAO;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.MD5Util;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * Created by lh
 * on 2017/9/11.
 */
@Service
public class UserService {
    Logger logger = Logger.getLogger(this.getClass().getName());
    private final UserDAO userDAO;
    private EmailService emailService;
    @Autowired
    public UserService(UserDAO userDAO,EmailService emailService) {
        this.userDAO = userDAO;
        this.emailService = emailService;
    }

    public boolean validateLogin(User user){
        user.setPassword(MD5Util.getMD5(user.getPassword()));
        User userForValidate = userDAO.getUserByLoginName(user.getLoginName());
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
    public boolean updateUserByIdAndValidateCode(User user){
        Timestamp now = new Timestamp(System.currentTimeMillis());
        /*if(now.getNanos() - user.getValidateDate().getNanos() /(60 * 60 * 1000) <= 3){
            return false;
        }else {
            userDAO.updateUserByIdAndValidateCode(user);
            return true;
        }*/
        return true;

    }
}
