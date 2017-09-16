package service;

import dao.CategoryAndLabelDAO;
import model.Category;
import model.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lh
 * on 2017/9/11.
 */
@Service
public class CategoryAndLabelService {
    @Autowired
    private CategoryAndLabelDAO categoryAndLabelDAO;

    public int saveCategory(Category category){
        return categoryAndLabelDAO.saveCategory(category);
    }

    public int saveLabel(Label label){
        return categoryAndLabelDAO.saveLabel(label);
    }

    /**
     * 获取所有类别
     * @return
     */
    public List<Category> getCategory(){
        return categoryAndLabelDAO.getCategory();
    }

    public List<Label> getLabelByCategoryId(int idCategory){
        return categoryAndLabelDAO.getLabelByCategoryId(idCategory);
    }
}
