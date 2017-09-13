package mapper;

import model.Article;
import model.Ebook;
import model.Web;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/9/13.
 */
@Repository
public interface ShareMapper {
    public int saveWeb(Web web);
    public int saveArticle(Article article);
    public int saveEbook(Ebook ebook);
    public List<Web> selectWebForIndex();
    public List<Article> selectArticleForIndex();
    public List<Ebook> selectEbookForIndex();
    public Article selectArticleById(int id);
}
