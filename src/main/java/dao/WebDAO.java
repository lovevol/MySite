package dao;

import mapper.ShareMapper;
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
    private ShareMapper shareMapper;

    /**
     * 获取主页的网站分享
     * @return
     */
    public List<Web> getWebForIndex(){
        return shareMapper.selectWebForIndex();
    }

    /**
     * 添加网站
     * @param web
     * @return
     */
    public int addWeb(Web web){
        return shareMapper.saveWeb(web);
    }
}
