package test;
/**
 * Created by lh
 * on 2017/9/6.
 */
import model.Article;
import valueobject.ArticleVO;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * JavaMail 版本: 1.6.0
 * JDK 版本: JDK 1.7 以上（必须）
 */
public class MainTest {
    public static void main(String[] args) {
        ArticleVO articleVO = new ArticleVO();
        Article article = new Article();
        article.setDate(new Date(System.currentTimeMillis()));
        article.setIdArticle(9898);
        try {
            articleVO.editArticleVO(article);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        System.out.println(articleVO.getDate());
        System.out.println(articleVO.getIdArticle());
    }
}


