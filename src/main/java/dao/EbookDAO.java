package dao;

import mapper.ShareMapper;
import model.Ebook;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by lh
 * on 2017/9/11.
 */
@Component
public class EbookDAO {
    @Autowired
    private ShareMapper shareMapper;

    /**
     * 添加电子书
     * @param ebook
     * @return
     */
    public int addEbook(Ebook ebook){
        return shareMapper.saveEbook(ebook);
    }

    /**
     * 获取主页的电子书分享
     * @return
     */
    public List<Ebook> getBookForIndex(){
       return shareMapper.selectEbookForIndex();
    }
}
