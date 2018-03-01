package mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.springframework.stereotype.Component;

@Component
public class MongoDBBase {
    static MongoClient mongoClient;
    static MongoDatabase mongoDatabase;
    //连接客户端并获取集合
    static {
        mongoClient = new MongoClient("120.24.4.136",27017);
        mongoDatabase = mongoClient.getDatabase("mysite");
    }
    public static MongoCollection getCollection(String name){
        return mongoDatabase.getCollection(name);
    }


}
