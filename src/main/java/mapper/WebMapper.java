package mapper;

import model.Web;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author Administrator
 * @date 2017/9/15
 */
@Repository
public interface WebMapper {
    /**
     * 添加ｗｅｂ
     * @param web
     * @return
     */
    int saveWeb(Web web);

    /**
     *获取主页显示的ｗｅｂ
     * @param keyWord
     * @return
     */
    List<Web> selectWebForIndex(@Param("keyWord") String keyWord);
}
