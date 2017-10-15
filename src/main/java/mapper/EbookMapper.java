package mapper;

import model.Ebook;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author Administrator
 * @date 2017/9/15
 */
@Repository
public interface EbookMapper {
    /**
     * 保存电子书
     * @param ebook
     * @return
     */
    int saveEbook(Ebook ebook);

    /**
     * 获取主页的文章
     * @param keyWord
     * @return
     */
    List<Ebook> selectEbookForIndex(@Param("keyWord") String keyWord);

    /**
     * 获取所有电子书
     * @return
     */
    List<Ebook> selectAllEbook();
}

