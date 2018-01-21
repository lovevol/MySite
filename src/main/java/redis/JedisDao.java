package redis;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.net.URL;
import java.util.List;

@Component
public class JedisDao {
    private final JedisClient client;

    @Autowired
    public JedisDao(JedisClient client) {
        this.client = client;
    }

    public boolean checkLogin(User user){
        String result = client.getClient().get(String.valueOf(user.getIdUser()));
        return result != null;
    }

    public void login(User user){
        client.getClient().set(String.valueOf(user.getIdUser()),String.valueOf(System.currentTimeMillis()));
    }

    public void setRecordForBrowsing(User user, String url){
        client.getClient().lpush(String.valueOf(user.getIdUser()),url);
    }

    public List<String> getRecordForBrowsing(User user){
        return client.getClient().lrange(String.valueOf(user.getIdUser()),0,-1);
    }
}
