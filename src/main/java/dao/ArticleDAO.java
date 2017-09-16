package dao;
import mapper.ArticleMapper;
import model.Article;
import model.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by lh
 * on 2017/9/11.
 */
@Component
public class ArticleDAO {
    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 保存文章
     * @param article
     * @return
     */
    public int addArticle(Article article){
        return articleMapper.saveArticle(article);
    }

    /**
     * 获取主页的文章
     * @return
     */
    public List<Article> getArticlesForIndex(){
       return articleMapper.selectArticleForIndex();
    }

    /**
     * 按照id查找文章
     * @param id
     * @return
     */
    public Article getArticleById(int id){
      return articleMapper.selectArticleById(id);
    }

    /**
     * 保存文章的正文内容
     * @param content
     * @return
     */
    public int addContent(Content content){
        return articleMapper.saveContent(content);
    }
}
