package dao;

import Utils.MyBatisUtil;
import model.Article;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by lh
 * on 2017/9/11.
 */
@Component
public class ArticleDAO {
    private SqlSession sqlSession = MyBatisUtil.getSqlSession();

    /**
     * 添加文章
     * @param article
     * @return
     */
    public int addArticle(Article article){
        int id = sqlSession.insert("mapper.ShareMapper.saveArticle",article);
        sqlSession.commit();
        return id;
    }

    /**
     * 获取主页的文章
     * @return
     */
    public List<Article> getArticlesForIndex(){
        List<Article> articles = sqlSession.selectList("mapper.ShareMapper.selectArticleForIndex");
        sqlSession.commit();
        return articles;
    }

    /**
     * 按照id查找文章
     * @param id
     * @return
     */
    public Article getArticleById(int id){
        Article article = sqlSession.selectOne("mapper.ShareMapper.selectArticleById",id);
        sqlSession.commit();
        return article;
    }
}
