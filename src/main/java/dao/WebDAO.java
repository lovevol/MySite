package dao;

import mapper.WebMapper;
import model.Article;
import model.Web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import valueobject.ArticleVO;

import java.util.List;

/**
 * Created by lh
 * on 2017/9/11.
 * @author lh
 */
@Component
public class WebDAO {
    @Autowired
    private WebMapper webMapper;

    /**
     * 获取主页的网站分享
     * @return
     */
    public List<Web> getWebForIndex(String keyWord){
        return webMapper.selectWebForIndex(keyWord);
    }

    /**
     * 添加网站
     * @param web
     * @return
     */
    public int addWeb(Web web){
        return webMapper.saveWeb(web);
    }

    /**
     * 选择所有Web
     * @return
     */
    public List<Web> selectAllWeb(){
        return webMapper.selectAllWeb();
    }

    /**
     * 按照id删除web
     * @param id
     * @return
     */
    public int deleteWebById(int id){
        return webMapper.deleteWebById(id);
    }

}
