package mapper;

import model.Article;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/9/15.
 */
@Repository
public interface ArticleMapper {
    public int saveArticle(Article article);
    public Article selectArticleById(int id);
    public List<Article> selectArticleForIndex();
}
