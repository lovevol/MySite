package dao;

import model.Web;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by lh
 * on 2017/9/11.
 */
@Component
public class WebDAO {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    /**
     * 获取主页的网站分享
     * @return
     */
    public List<Web> getWebForIndex(){
        List<Web> webs = sqlSessionTemplate.selectList("mapper.ShareMapper.selectWebForIndex");
        return webs;
    }

    /**
     * 添加网站
     * @param web
     * @return
     */
    public int addWeb(Web web){
        int id = sqlSessionTemplate.insert("mapper.ShareMapper.saveWeb",web);
        return id;
    }
}
