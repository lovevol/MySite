package service;

import dao.UserDAO;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lh
 * on 2017/9/11.
 */
@Service
public class UserService {
    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean validateLogin(User user){
        User userForValidate = userDAO.getUserByLoginName(user.getLoginName());
        if (userForValidate != null && user.getPassword().equals(userForValidate.getPassword()) &&
                user.getRoleType() == userForValidate.getRoleType()){
            return true;
        }else {
            return false;
        }
    }
}
