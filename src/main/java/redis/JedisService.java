package redis;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JedisService {
    private final JedisDao jedisDao;

    @Autowired
    public JedisService(JedisDao jedisDao) {
        this.jedisDao = jedisDao;
    }

    public boolean checkLogin(User user){
        return jedisDao.checkLogin(user);
    }
    public void setRecordForBrowsing(User user,String url){
        if (user == null || url == null)
            return;
        jedisDao.setRecordForBrowsing(user,url);
    }
    public List<String> getRecordForBrowsing(User user){
        if (user == null){
            return null;
        }
        return jedisDao.getRecordForBrowsing(user);
    }
}
