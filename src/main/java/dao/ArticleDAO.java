package dao;

import model.Article;
import org.mybatis.spring.SqlSessionTemplate;
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
    private SqlSessionTemplate sqlSessionTemplate;

    /**
     * 添加文章
     * @param article
     * @return
     */
    public int addArticle(Article article){
        return sqlSessionTemplate.insert("mapper.ShareMapper.saveArticle",article);
    }

    /**
     * 获取主页的文章
     * @return
     */
    public List<Article> getArticlesForIndex(){
        List<Article> articles = sqlSessionTemplate.selectList("mapper.ShareMapper.selectArticleForIndex");
        return articles;
    }

    /**
     * 按照id查找文章
     * @param id
     * @return
     */
    public Article getArticleById(int id){
        Article article = sqlSessionTemplate.selectOne("mapper.ShareMapper.selectArticleById",id);
        return article;
    }
}
