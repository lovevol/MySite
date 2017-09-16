package dao;

import mapper.CategoryAndLabelMapper;
import model.Category;
import model.Label;
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
    public int saveCategory(Category category){
        return categoryAndLabelMapper.saveCategory(category);
    }

    public int saveLabel(Label label){
        return categoryAndLabelMapper.saveLabel(label);
    }

    /**
     * 查询所有类别
     * @return
     */
    public List<Category> getCategory(){
        return categoryAndLabelMapper.selectCategory();
    }

    /**
     * 按照类别查询标签
     * @param
     * @return
     */
    public List<Label> getLabelByCategoryId(int idCategory){
        return categoryAndLabelMapper.selectLabelByCategoryId(idCategory);
    }

    public int updateLabelForAddArticle(Label label){
        return categoryAndLabelMapper.updateLabelForAddArticle(label);
    }

    public Label getLabelById(int id){
        return categoryAndLabelMapper.selectLabelById(id);
    }
}
