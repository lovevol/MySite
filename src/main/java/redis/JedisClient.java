package redis;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

@Component
public class JedisClient {
    private final static Jedis client = new Jedis("127.0.0.1",6379);
   public Jedis getClient(){
       return client;
   }

}
