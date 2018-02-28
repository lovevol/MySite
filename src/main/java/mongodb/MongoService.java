package mongodb;

import model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MongoService {
    @Autowired
    private MongoDBDao mongoDBDao;
    public void saveComment(Comment comment){
        mongoDBDao.saveComment(comment);
    }
    public List<Comment> getCommentByArticleId(int id){
        return mongoDBDao.getCommentByArticleId(id);
    }
}
