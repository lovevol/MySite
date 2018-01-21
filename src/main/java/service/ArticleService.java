package service;

import dao.ArticleDAO;
import dao.CategoryAndLabelDAO;
import model.Article;
import model.Content;
import model.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import valueobject.ArticleVO;

import java.util.List;

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
        Label label = categoryAndLabelDAO.getLabelById(article.getLabel().getIdLabel());
        label.setArticleNum(label.getArticleNum() + 1);
        categoryAndLabelDAO.updateLabelForAddArticle(label);
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

    public List<Article> getArticleByCategory(int idCategory){
        return articleDAO.getArticleByCategory(idCategory);
    }

    public int deleteArticleById(int id){
        Article article = getArticleById(id);
        categoryAndLabelDAO.updateLabelForDeleteArticle(article.getLabel().getIdLabel());
        return articleDAO.deleteArticleById(id);
    }

    public int updateArticle(Article article){
        Content content = article.getContent();
        if (content != null && content.getContent() != null){
            articleDAO.updateContent(content);
        }
        return articleDAO.updateArticle(article);
    }
}
