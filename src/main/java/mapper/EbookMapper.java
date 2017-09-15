package mapper;

import model.Ebook;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/9/15.
 */
@Repository
public interface EbookMapper {
    public int saveEbook(Ebook ebook);
    public List<Ebook> selectEbookForIndex();
}
