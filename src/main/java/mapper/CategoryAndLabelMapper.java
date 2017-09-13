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
    public List<Category> selectCategoryByType(int type);
    public Category selectCategoryById(int id);
    public List<Label> selectLabelByCategory(int category);
}
