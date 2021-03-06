package dao;
import mapper.ArticleMapper;
import model.Article;
import model.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import value.ArticleVO;

import java.util.List;
import java.util.Set;

/**
 * Created by lh
 * on 2017/9/11.
 * @author lh
 */
@Component
public class ArticleDAO {
    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 保存文章
     * @param article 文章
     * @return 结果
     */
    public int addArticle(Article article){
        return articleMapper.saveArticle(article);
    }

    /**
     * 获取主页的文章
     * @return 文章列表
     */
    public List<Article> getArticlesForIndex(String keyWord){
       return articleMapper.selectArticleForIndex(keyWord);
    }

    /**
     * 按照id查找文章
     * @param id id
     * @return 文章列表
     */
    public Article getArticleById(int id){
      return articleMapper.selectArticleById(id);
    }

    /**
     * 保存文章的正文内容
     * @param content 正文内容
     * @return 结果
     */
    public int addContent(Content content){
        return articleMapper.saveContent(content);
    }

    /**
     * 按照条件搜索文章
     * @param articleVO value类
     * @return 文章列表
     */
    public List<Article> getArticle(ArticleVO articleVO){
        return articleMapper.selectArticle(articleVO);
    }

    /**
     * 按照类别搜索文章
     * @param idCategory 文章id
     * @return
     */
     public List<Article> getArticleByCategory(int idCategory){
        return articleMapper.selectArticleByCategory(idCategory);
     }

    /**
     * 按照id删除文章
     * @param id 文章id
     * @return
     */
     public int deleteArticleById(int id){
         return articleMapper.deleteArticleById(id);
     }

    /**
     * 按照id更新文章
     * @param article 更新非空字段数据（必须带有id）
     * @return
     */
     public int updateArticle(Article article){
         return articleMapper.updateArticle(article);
     }

    /**
     * 更新文章内容
     * @param content
     * @return
     */
     public int updateContent(Content content){
         return articleMapper.updateContent(content);
     }

    /**
     * 按照标签id获取文章
     * @param id
     * @return
     */
     public List<Article> getArticleByLabelId(int id){
         return articleMapper.selectArticleByLabelId(id);
     }

    /**
     * 获取特定id的文章
     * @param ids
     * @return
     */
    public List<Article> selectArticleForUserSave(Set<String> ids){
         return articleMapper.selectArticleForUserSave(ids);
    }
}
