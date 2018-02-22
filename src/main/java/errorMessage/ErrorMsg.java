package errorMessage;

/**
 * Created by lh
 * on 2018/2/22.
 */
public class ErrorMsg {
    private String title;
    private String content;
    public ErrorMsg(){

    }
    public ErrorMsg(String title,String content){
        this.title = title;
        this.content = content;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
