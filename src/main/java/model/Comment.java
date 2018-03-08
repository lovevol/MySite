package model;


import java.util.Date;
public class Comment {
    private String id;
    private int articleId;
    private int userId;
    private String content;
    private Date date;

    public Comment(){

    }
    public Comment(String id, int articleId, int userId, String content, Date date) {
        this.id = id;
        this.articleId = articleId;
        this.userId = userId;
        this.content = content;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
