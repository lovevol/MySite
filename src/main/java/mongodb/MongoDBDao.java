package mongodb;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MongoDBDao {
    private static final String COLLECTION_NAME = "comments";
    public void saveComment(Comment comment){
        MongoCollection<Document> collection = MongoDBBase.getCollection(COLLECTION_NAME);
        Document commentD = new Document().append("userId",comment.getUserId())
                .append("articleId",comment.getArticleId())
                .append("content",comment.getContent())
                .append("date",comment.getDate());
        collection.insertOne(commentD);
    }

    public List<Comment> getCommentByArticleId(int id){
        MongoCollection<Document> collection = MongoDBBase.getCollection(COLLECTION_NAME);
        FindIterable<Document> documents = collection.find(Filters.eq("articleId",id));
        List<Comment> comments = new ArrayList<>();
        for (Document document:documents){
            Comment comment = new Comment();
            comment.setArticleId(document.getInteger("articleId"));
            comment.setContent(document.getString("content"));
            comment.setDate(document.getDate("date"));
            comment.setUserId(document.getInteger("userId"));
            comments.add(comment);
        }
        return comments;
    }
}
