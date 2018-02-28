package mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import model.Comment;
import org.bson.Document;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MongoDBBase {
    static MongoClient mongoClient;
    static MongoDatabase mongoDatabase;
    //连接客户端并获取集合
    static {
        mongoClient = new MongoClient("localhost",27017);
        mongoDatabase = mongoClient.getDatabase("mysite");
    }
    public static MongoCollection getCollection(String name){
        return mongoDatabase.getCollection(name);
    }


}
