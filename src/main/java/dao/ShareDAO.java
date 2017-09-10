package dao;

import Utils.MyBatisUtil;
import model.Article;
import model.Ebook;
import model.Web;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Created by lh
 * on 2017/9/9.
 */
public class ShareDAO {
    private SqlSession sqlSession = MyBatisUtil.getSqlSession();
    public List<Web> getAllWeb(){
        List<Web> webs = sqlSession.selectList("mapper.ShareMapper.selectWeb");
        sqlSession.commit();
        return webs;
    }
    public int addWeb(Web web){
        int id = sqlSession.insert("mapper.ShareMapper.saveWeb",web);
        sqlSession.commit();
        return id;
    }
    public int addEbook(Ebook ebook){
        int id = sqlSession.insert("mapper.ShareMapper.saveEbook",ebook);
        sqlSession.commit();
        return id;
    }
    public List<Ebook> getAllBook(){
        List<Ebook> ebooks = sqlSession.selectList("mapper.ShareMapper.selectEbook");
        sqlSession.commit();
        return ebooks;
    }
    public int addArticle(Article article){
        int id = sqlSession.insert("mapper.ShareMapper.saveArticle",article);
        sqlSession.commit();
        return id;
    }
    public List<Article> getArticlesForIndex(){
        List<Article> articles = sqlSession.selectList("mapper.ShareMapper.selectArticleForIndex");
        sqlSession.commit();
        return articles;
    }
    public Article getArticleById(int id){
        Article article = sqlSession.selectOne("mapper.ShareMapper.selectArticleById",id);
        sqlSession.commit();
        return article;
    }
}
