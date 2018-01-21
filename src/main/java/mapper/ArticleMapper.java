package mapper;

import model.Article;
import model.Content;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import valueobject.ArticleVO;

import java.util.List;

/**
 * Created by Administrator on 2017/9/15.
 * @author lh
 */
@Repository
public interface ArticleMapper {
    /**
     * 保存文章
     * @param article　文章
     * @return　
     */
    int saveArticle(Article article);

    /**
     * 按照id选择文章
     * @param id　ｉｄ
     * @return　articel
     */
    Article selectArticleById(int id);

    /**
     * 获取主页搜索的文章
     * @param keyWord　关键字
     * @return　文章
     */
    List<Article> selectArticleForIndex(@Param("keyWord") String keyWord);

    /**
     * 按照文章ｉｄ选择内容
     * @param id　文章ｉｄ
     * @return　内容
     */
    Content selectContentByArticleId(int id);

    /**
     * 保存文章的内容
     * @param content　内容
     * @return　结果
     */
    int saveContent(Content content);

    /**
     * 按照传入的条件搜索文章
     * @param articleVO　ｖｏ
     * @return　文章
     */
    List<Article> selectArticle(ArticleVO articleVO);

    List<Article> selectArticleByCategory(int idCategory);

    int deleteArticleById(int id);

    int updateArticle(Article article);

    int updateContent(Content content);
}
