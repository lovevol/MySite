package mapper;

import model.Category;
import model.Label;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/9/13.
 * @author lh
 */
@Repository
public interface CategoryAndLabelMapper {
    /**
     * 按照类型选择分类
     * @param type　类型　1.文章分享　２.资源分享
     * @return　类别
     */
    List<Category> selectCategoryByType(int type);

    /**
     * 按照ｉｄ选择类别
     * @param id　ｉｄ
     * @return
     */
    Category selectCategoryById(int id);

    /**
     * 选择分类下的标签
     * @param idCategory
     * @return
     */
    List<Label> selectLabelByCategoryId(int idCategory);

    /**
     * 保存标签
     * @param label
     * @return
     */
    int saveLabel(Label label);

    /**
     * 保存类别
     * @param category
     * @return
     */
    int saveCategory(Category category);

    /**
     * 选择所有类别
     * @return
     */
    List<Category> selectCategory();

    /**
     * 按照ｉｄ选择标签
     * @param id
     * @return
     */
    Label selectLabelById(int id);

    /**
     *
     * @param id
     * @return
     */
    Category selectCategoryByIdForLabel(int id);

    /**
     * 添加文章时更新该ｌａｂｅｌ下的文章数量
     * @param label
     * @return
     */
    int updateLabelForAddArticle(Label label);

    /**
     * 更新类别
     * @param category
     * @return
     */
    int updateCategory(Category category);

    /**
     * 按照ｉｄ删除类别
     * @param idCategory
     * @return
     */
    int deleteCategoryById(int idCategory);

    /**
     * 获取所有标签
     * @return
     */
    List<Label> selectLabel();

    /**
     * 按照ｉｄ删除标签
     * @param id
     * @return
     */
    int deleteLabelById(int id);

    void updateLabelForDeleteArticle();
}
