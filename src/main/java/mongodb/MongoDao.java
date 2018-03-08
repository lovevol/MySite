package mongodb;

import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MongoDao {
    private MongoTemplate mongoTemplate;

    public String save(Comment comment){

        mongoTemplate.save(comment);
        return comment.getId();
    }

    public long update(Comment comment){
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(comment.getId()).and("userId").is(comment.getUserId()));
        Update update = new Update();
        update.set("date",new java.util.Date());
        update.set("content",comment.getContent());
        UpdateResult result = mongoTemplate.updateFirst(query, update, Comment.class);
        return result.getModifiedCount();
    }

    public List<Comment> getByArticleId(int articleId){
        return mongoTemplate.find(Query.query(Criteria.where("articleId").is(articleId)),Comment.class);
    }

    public void removeCommentById(String id){
        mongoTemplate.remove(
                Query.query(Criteria.where("id").is(id)), Comment.class);
    }

}
