package redis;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.net.URL;
import java.util.List;

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

    public void setRecordForBrowsing(User user, String url){
        Jedis jedis = getJedis();
        jedis.lpush(String.valueOf(user.getIdUser()),url);
        close(jedis);

    }

    public List<String> getRecordForBrowsing(User user){
        Jedis jedis = getJedis();
        List<String> list = jedis.lrange(String.valueOf(user.getIdUser()),0,-1);
        close(jedis);
        return  list;
    }

    private Jedis getJedis(){
        return RedisUtil.getJedis();
    }
    private void close(Jedis jedis){
        RedisUtil.returnResource(jedis);
    }
}
