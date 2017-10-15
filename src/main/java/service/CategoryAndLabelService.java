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
 * @author lh
 */
@Service
public class CategoryAndLabelService {
    @Autowired
    private CategoryAndLabelDAO categoryAndLabelDAO;

    public int addCategory(Category category){
        return categoryAndLabelDAO.saveCategory(category);
    }

    public int addLabel(Label label){
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

    public List<Category> getCategoryByType(int type){
        return categoryAndLabelDAO.getCategoryByType(type);
    }

    public int updateLabelForAddArticle(Label label){
        return categoryAndLabelDAO.updateLabelForAddArticle(label);
    }

    public int updateCategory(Category category){
        return categoryAndLabelDAO.updateCategory(category);
    }

    public int deleteCategoryById(int idCategory){
        return categoryAndLabelDAO.deleteCategoryById(idCategory);
    }
    public List<Label> getLabel(){
        return categoryAndLabelDAO.getLabel();
    }
    public int deleteLabelById(int id){
        return categoryAndLabelDAO.deleteLabelById(id);
    }
}
