package value_object;

import model.Article;
import model.Category;
import model.Content;
import model.Label;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by lh
 * on 2017/9/11.
 */
public class ArticleVO extends BaseVO {
    private int idArticle;
    private String title;
    private Timestamp date;
    private String imagePath;
    private String sketch;
    private Category category;
    private Label label;
    private Content content;

    public void editArticleVO(Article article) throws InvocationTargetException, IllegalAccessException, InstantiationException {

        //获取Article的域和方法
        Field[] fieldsOfArticle = Article.class.getDeclaredFields();
        Method[] methodsOfArticle = Article.class.getDeclaredMethods();
        Set<Field> fieldsOfArticleSet = new HashSet<>();
        Collections.addAll(fieldsOfArticleSet,fieldsOfArticle);
        Set<Method> methodsOfArticleSet = new HashSet<>();
        Collections.addAll(methodsOfArticleSet,methodsOfArticle);
        //获取Article的变量名称
        String[] fieldNamesOfArticle = new String[fieldsOfArticle.length];
        Set<String> fieldNamesOfArticleSet = new HashSet<>();
        for (int i = 0; i < fieldsOfArticle.length; i ++){
            fieldNamesOfArticle[i] = fieldsOfArticle[i].getName();
        }
        Collections.addAll(fieldNamesOfArticleSet,fieldNamesOfArticle);

        //获取ArticleVO的域和方法
        Field[] fieldsOfArticleVO = ArticleVO.class.getDeclaredFields();
        Method[] methodsOfArticleVO = ArticleVO.class.getDeclaredMethods();
        Set<Field> fieldsOfArticleVOSet = new HashSet<>();
        Collections.addAll(fieldsOfArticleVOSet,fieldsOfArticleVO);
        Set<Method> methodsOfArticleVOSet = new HashSet<>();
        Collections.addAll(methodsOfArticleVOSet,methodsOfArticleVO);

        Iterator<Method> methodOfArticleVOIterator = methodsOfArticleVOSet.iterator();
        Iterator<Method> methodOfArticleIterator = methodsOfArticleSet.iterator();

        while (methodOfArticleVOIterator.hasNext()){
            Method methodOfArticleVO = methodOfArticleVOIterator.next();
            String methodNameOfArticleVO = methodOfArticleVO.getName();
            String methodPreFixOfArticleVo = methodNameOfArticleVO.substring(0,3);
            String methodForFieldName = methodNameOfArticleVO.substring(3,4).toLowerCase()+methodNameOfArticleVO.substring(4);
            if ("set".equals(methodPreFixOfArticleVo) && fieldNamesOfArticleSet.contains(methodForFieldName)){
                for (int i = 0; i < methodsOfArticle.length; i ++){
                    Method method = methodsOfArticle[i];
                    String reqMethodName = "get"+methodForFieldName.substring(0,1).toUpperCase()+methodForFieldName.substring(1);
                    if (method.getName().equals(reqMethodName)){
                        methodOfArticleVO.invoke(this,method.invoke(article));
                    }
                }
            }
        }

    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getSketch() {
        return sketch;
    }

    public void setSketch(String sketch) {
        this.sketch = sketch;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }
}
