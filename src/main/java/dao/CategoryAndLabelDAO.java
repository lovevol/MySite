package dao;

import mapper.CategoryAndLabelMapper;
import model.Category;
import model.Label;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by lh
 * on 2017/9/10.
 */
@Component
public class CategoryAndLabelDAO {
    @Autowired
    private CategoryAndLabelMapper categoryAndLabelMapper;

    public List<Category> getCategoryByType(int type){
       return categoryAndLabelMapper.selectCategoryByType(type);
    }
    public List<Label> getLabelByCategory(int category){
        return categoryAndLabelMapper.selectLabelByCategory(category);
    }
}
