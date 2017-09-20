package mapper;

import model.Article;
import model.Content;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import valueobject.ArticleVO;

import java.util.List;

/**
 * Created by Administrator on 2017/9/15.
 */
@Repository
public interface ArticleMapper {
    int saveArticle(Article article);
    Article selectArticleById(int id);
    List<Article> selectArticleForIndex(@Param("keyWord") String keyWord);
    Content selectContentByArticleId(int id);
    int saveContent(Content content);
    List<Article> selectArticle(ArticleVO articleVO);
}
