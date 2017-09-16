package mapper;

import model.Article;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/9/15.
 */
@Repository
public interface ArticleMapper {
    int saveArticle(Article article);
    Article selectArticleById(int id);
    List<Article> selectArticleForIndex();
}
