package redis;

import model.Article;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

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
    public void setViewNumOfArticle(Article article){
        jedisDao.setViewNumOfArticle(article);
    }
    public String getViewNumOfArticle(Article article){
        return jedisDao.getViewNumOfArticle(article);
    }
    public Long saveArticleForUser(int userId,int articleId){
        return jedisDao.saveArticleForUser(userId,articleId);
    }
    public Set<String> getArticleForUser(int userId){
        return jedisDao.getArticleForUser(userId);
    }
    public Long cancelArticleSave(int userId,int articleId){
        return jedisDao.cancelArticleSave(userId,articleId);
    }
}
