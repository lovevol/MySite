package PagingPlugin;

public interface PageParams {

    public Integer getPage();

    public void setPage(Integer page);

    public Integer getPageSize();

    public void setPageSize(Integer pageSize);

    public Boolean getUseFlag();

    public void setUseFlag(Boolean useFlag);

    public Boolean getCheckFlag();

    public void setCheckFlag(Boolean checkFlag);

    public Integer getTotal();

    public void setTotal(Integer total);

    public Integer getTotalPage();

    public void setTotalPage(Integer totalPage);
}
