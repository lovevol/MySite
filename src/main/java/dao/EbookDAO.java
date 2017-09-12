package dao;

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
    private SqlSessionTemplate sqlSessionTemplate;

    /**
     * 添加电子书
     * @param ebook
     * @return
     */
    public int addEbook(Ebook ebook){
        int id = sqlSessionTemplate.insert("mapper.ShareMapper.saveEbook",ebook);
        return id;
    }

    /**
     * 获取主页的电子书分享
     * @return
     */
    public List<Ebook> getBookForIndex(){
        List<Ebook> ebooks = sqlSessionTemplate.selectList("mapper.ShareMapper.selectEbookForIndex");
        return ebooks;
    }
}
