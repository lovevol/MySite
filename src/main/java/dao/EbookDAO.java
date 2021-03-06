package dao;

import mapper.EbookMapper;
import model.Ebook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by lh
 * on 2017/9/11.
 * @author lh
 */
@Component
public class EbookDAO {
    @Autowired
    private EbookMapper ebookMapper;

    /**
     * 添加电子书
     * @param ebook
     * @return
     */
    public int addEbook(Ebook ebook){
        return ebookMapper.saveEbook(ebook);
    }

    /**
     * 获取主页的电子书分享
     * @return
     */
    public List<Ebook> getEbookForIndex(String keyWord){
       return ebookMapper.selectEbookForIndex(keyWord);
    }

    /**
     * 获取所有电子书
     * @return
     */
    public List<Ebook> selectAllEbook(){
        return ebookMapper.selectAllEbook();
    }

    /**
     * 按照id删除电子书
     * @param id
     * @return
     */
    public int deleteEbookById(int id){
        return ebookMapper.deleteEbookById(id);
    }
}
