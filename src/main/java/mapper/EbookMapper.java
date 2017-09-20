package mapper;

import model.Ebook;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/9/15.
 */
@Repository
public interface EbookMapper {
    int saveEbook(Ebook ebook);
    List<Ebook> selectEbookForIndex(@Param("keyWord") String keyWord);
}
