package service;

import dao.ArticleDAO;
import dao.CategoryAndLabelDAO;
import model.Article;
import model.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import value.ArticleVO;

import java.util.List;
import java.util.Set;

/**
 * Created by lh
 * on 2017/9/11.
 * @author lh
 */
@Service
public class ArticleService {
    @Autowired
    private ArticleDAO articleDAO;

    @Autowired
    private CategoryAndLabelDAO categoryAndLabelDAO;

    /**
     * 保存文章
     * @param article
     * @return
     */
    public int addArticle(Article article){
        Content content = article.getContent();
        //先储存文章正文内容到数据库
        articleDAO.addContent(content);
        //再储存文章到数据库
        int result = articleDAO.addArticle(article);
        //更新label的文章数目
        categoryAndLabelDAO.updateLabelForAddArticle(article.getLabel().getIdLabel());
        return result;
    }

    /**
     * 获取显示在主页的文章
     * @return
     */
    public List<Article> getArticleForIndex(String keyWord){
        return articleDAO.getArticlesForIndex(keyWord);
    }

    public Article getArticleById(int id){
        return articleDAO.getArticleById(id);
    }

    /**
     * 主页搜索
     * @param articleVO
     * @return
     */
    public List<Article> getArticle(ArticleVO articleVO){
        return articleDAO.getArticle(articleVO);
    }

    /**
     * 获取指定类别下的文章
     * @param idCategory 类别id
     * @return
     */
    public List<Article> getArticleByCategory(int idCategory){
        return articleDAO.getArticleByCategory(idCategory);
    }

    public int deleteArticleById(int id){
        Article article = getArticleById(id);
        categoryAndLabelDAO.updateLabelForDeleteArticle(article.getLabel().getIdLabel());
        return articleDAO.deleteArticleById(id);
    }

    /**
     * 更新文章
     * @param article
     * @return
     */
    public int updateArticle(Article article){
        //获取文章内容，若非空，更新文章内容
        Content content = article.getContent();
        if (content != null && content.getContent() != null && !content.getContent().equals("")){
            articleDAO.updateContent(content);
        }
        //如果标签改变，则修改标签下文章数量
        Article oldArticle = articleDAO.getArticleById(article.getIdArticle());
        if (!(oldArticle.getLabel().getIdLabel() == article.getLabel().getIdLabel())){
            categoryAndLabelDAO.updateLabelForAddArticle(article.getLabel().getIdLabel());
            categoryAndLabelDAO.updateLabelForDeleteArticle(oldArticle.getLabel().getIdLabel());
        }
        return articleDAO.updateArticle(article);
    }

    /**
     * 获取指定标签下的文章列表
     * @param id
     * @return
     */
    public List<Article> getArticleByLabelId(int id){
        return articleDAO.getArticleByLabelId(id);
    }

    /**
     * 获取用户的收藏文章列表
     * @param ids
     * @return
     */
    public List<Article> selectArticleForUserSave(Set<String> ids){
        return articleDAO.selectArticleForUserSave(ids);
    }
}
