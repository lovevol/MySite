package test;
/**
 * Created by lh
 * on 2017/9/6.
 */
import dao.UserDAO;
import mapper.UserMapper;
import model.Article;
import model.User;
import myenum.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import valueobject.ArticleVO;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * JavaMail 版本: 1.6.0
 * JDK 版本: JDK 1.7 以上（必须）
 */
public class MainTest {
    @Autowired
    private static UserDAO userDAO;
    public static void main(String[] args) {
        Random random = new Random(3);
        List<User> list = new LinkedList<>();
        for (int i = 1; i< 100;i++){
            User user = new User();
            user.setLoginName("lll" + random.nextInt(999));
            user.setUserName("lll");
            user.setPassword("aaaaa");
            user.setGender(Gender.FEMALE);
            user.setRoleType((byte)1);
            user.setEmail("aaaa");
            user.setValidateCode("56456");
            user.setStatus(1);
            user.setValidateDate(new Timestamp(System.currentTimeMillis()));
            list.add(user);
        }
        userDAO.addUserByBatch(list);

    }
}


