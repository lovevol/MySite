package model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by lh
 * on 2017/9/10.
 * @author lh
 */
public class Article {
    private int idArticle;
    private String title;
    private Date date;
    private String imagePath;
    private String sketch;
    private Category category;
    private Label label;
    private Content content;

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    @Override
    public String toString() {
        return "Article{" +
                "idArticle=" + idArticle +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", imagePath='" + imagePath + '\'' +
                ", sketch='" + sketch + '\'' +
                ", category=" + category +
                ", label=" + label +
                ", content=" + content +
                '}';
    }
}
