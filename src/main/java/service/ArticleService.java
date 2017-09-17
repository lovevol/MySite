package service;

import dao.ArticleDAO;
import dao.CategoryAndLabelDAO;
import model.Article;
import model.Content;
import model.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lh
 * on 2017/9/11.
 */
@Service
public class ArticleService {
    @Autowired
    private ArticleDAO articleDAO;

    @Autowired
    private CategoryAndLabelDAO categoryAndLabelDAO;

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

    public List<Article> getArticleForIndex(){
        return articleDAO.getArticlesForIndex();
    }

    public Article getArticleById(int id){
        return articleDAO.getArticleById(id);
    }
}
