package mapper;

import model.Category;
import model.Label;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/9/13.
 */
@Repository
public interface CategoryAndLabelMapper {
    List<Category> selectCategoryByType(int type);

    Category selectCategoryById(int id);

    List<Label> selectLabelByCategoryId(int idCategory);

    int saveLabel(Label label);

    int saveCategory(Category category);

    List<Category> selectCategory();

    Label selectLabelById(int id);

    Category selectCategoryByIdForLabel(int id);
}
