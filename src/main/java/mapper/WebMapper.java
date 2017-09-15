package mapper;

import model.Web;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/9/15.
 */
@Repository
public interface WebMapper {
    public int saveWeb(Web web);
    public List<Web> selectWebForIndex();
}
