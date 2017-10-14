package test;
/**
 * Created by lh
 * on 2017/9/6.
 */
import dao.UserDAO;
import model.Article;
import model.User;
import myenum.Gender;
import valueobject.ArticleVO;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * JavaMail 版本: 1.6.0
 * JDK 版本: JDK 1.7 以上（必须）
 */
public class MainTest {
    public static void main(String[] args) {
        User user = new User();
        user.setLoginName("lll");
        user.setUserName("lll");
        user.setPassword("aaaaa");
        user.setGender(Gender.FEMALE);
        user.setRoleType((byte)1);
        user.setEmail("aaaa");
        user.setValidateCode("56456");
        user.setStatus(1);
        user.setValidateDate(new Timestamp(System.currentTimeMillis()));
        UserDAO userDAO = new UserDAO();
        userDAO.addUser(user);
    }
}


