package model;

/**
 * Created by lh
 * on 2017/9/16.
 * @author lh
 */
public class Content {
    private int idContent;
    private String content;

    public int getIdContent() {
        return idContent;
    }

    public void setIdContent(int idContent) {
        this.idContent = idContent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Content{" +
                "idContent=" + idContent +
                ", content='" + content + '\'' +
                '}';
    }
}
