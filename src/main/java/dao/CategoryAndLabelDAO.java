package dao;

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
    private SqlSessionTemplate sqlSessionTemplate;

    public List<Category> getCategoryByType(int type){
        List<Category> categories = sqlSessionTemplate.selectList("mapper.CategoryAndLabelMapper.selectCategoryByType",
                type);
        return categories;
    }
    public List<Label> getLabelByCategory(int category){
        List<Label> labels = sqlSessionTemplate.selectList("mapper.CategoryAndLabelMapper.selectLabelByCategory",
                category);
        return labels;
    }
}
