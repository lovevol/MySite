package model;

/**
 * Created by lh
 * on 2017/9/9.
 */

/**
 * 网站
 */
public class Web {
    private int idWeb;
    private String webUrl;
    private String description;
    private String remark;
    private String label;

    public int getIdWeb() {
        return idWeb;
    }

    public void setIdWeb(int idWeb) {
        this.idWeb = idWeb;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "Web{" +
                "idWeb=" + idWeb +
                ", webUrl='" + webUrl + '\'' +
                ", description='" + description + '\'' +
                ", remark='" + remark + '\'' +
                ", label='" + label + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Web web = (Web) o;

        if (idWeb != web.idWeb) return false;
        if (webUrl != null ? !webUrl.equals(web.webUrl) : web.webUrl != null) return false;
        if (description != null ? !description.equals(web.description) : web.description != null) return false;
        if (remark != null ? !remark.equals(web.remark) : web.remark != null) return false;
        return label != null ? label.equals(web.label) : web.label == null;
    }

    @Override
    public int hashCode() {
        int result = idWeb;
        result = 31 * result + (webUrl != null ? webUrl.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (label != null ? label.hashCode() : 0);
        return result;
    }
}
