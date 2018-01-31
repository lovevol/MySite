package redis;

import model.Article;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import util.Constant;

import java.net.URL;
import java.util.List;
import java.util.Set;

@Component
public class JedisDao {
    public boolean checkLogin(User user){
        Jedis jedis = getJedis();
        String result = jedis.get(String.valueOf(user.getIdUser()));
        close(jedis);
        return result != null;
    }

    public void login(User user){
        Jedis jedis = getJedis();
        jedis.set(String.valueOf(user.getIdUser()),String.valueOf(System.currentTimeMillis()));
        close(jedis);
    }

    //浏览记录保存
    public void setRecordForBrowsing(User user, String url){
        Jedis jedis = getJedis();
        jedis.lpush(String.valueOf(user.getIdUser()),url);
        close(jedis);

    }

    //浏览记录获取
    public List<String> getRecordForBrowsing(User user){
        Jedis jedis = getJedis();
        List<String> list = jedis.lrange(String.valueOf(user.getIdUser()),0,-1);
        close(jedis);
        return  list;
    }

    //记录文章浏览量
    public void setViewNumOfArticle(Article article){
        Jedis jedis = getJedis();
        String key = String.valueOf(article.getIdArticle())+ Constant.ARTICLE_VIEW_NUM;
        String result = jedis.get(key);
        if (result == null){
            jedis.set(key,"0");
        }else {
            jedis.incr(key);
        }
        close(jedis);
    }

    //获取文章浏览量
    public String getViewNumOfArticle(Article article){
        Jedis jedis = getJedis();
        String key = String.valueOf(article.getIdArticle())+ Constant.ARTICLE_VIEW_NUM;
        String result = jedis.get(key);
        close(jedis);
        return result == null?"0":result;
    }

    public Long saveArticleForUser(int userId,int articleId){
        Jedis jedis = getJedis();
        Long result = jedis.sadd(userId + Constant.USER_SAVE_ARTICLE,String.valueOf(articleId));
        close(jedis);
        return result;
    }

    public Set<String> getArticleForUser(int userId){
        Jedis jedis = getJedis();
        Set<String> ids = jedis.smembers(userId+Constant.USER_SAVE_ARTICLE);
        close(jedis);
        return ids;
    }
    public Long cancelArticleSave(int userId,int articleId){
        Jedis jedis = getJedis();
        Long result = jedis.srem(userId+Constant.USER_SAVE_ARTICLE,String.valueOf(articleId));
        close(jedis);
        return result;
    }
    private Jedis getJedis(){
        return RedisUtil.getJedis();
    }
    private void close(Jedis jedis){
        RedisUtil.returnResource(jedis);
    }
}
