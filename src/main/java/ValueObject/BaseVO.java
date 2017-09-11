package ValueObject;

/**
 * Created by lh
 * on 2017/9/11.
 */
public class BaseVO {
    private int pageSize;
    private int currentPage;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }


}
