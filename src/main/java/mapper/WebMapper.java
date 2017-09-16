package mapper;

import model.Web;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/9/15.
 */
@Repository
public interface WebMapper {
    int saveWeb(Web web);
    List<Web> selectWebForIndex();
}
