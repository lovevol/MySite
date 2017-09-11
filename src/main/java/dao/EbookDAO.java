package dao;

import Utils.MyBatisUtil;
import model.Ebook;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by lh
 * on 2017/9/11.
 */
@Component
public class EbookDAO {
    private SqlSession sqlSession = MyBatisUtil.getSqlSession();

    /**
     * 添加电子书
     * @param ebook
     * @return
     */
    public int addEbook(Ebook ebook){
        int id = sqlSession.insert("mapper.ShareMapper.saveEbook",ebook);
        sqlSession.commit();
        return id;
    }

    /**
     * 获取主页的电子书分享
     * @return
     */
    public List<Ebook> getBookForIndex(){
        List<Ebook> ebooks = sqlSession.selectList("mapper.ShareMapper.selectEbookForIndex");
        sqlSession.commit();
        return ebooks;
    }
}
