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
 * @author lh
 */
@Component
public class CategoryAndLabelDAO {
    @Autowired
    private CategoryAndLabelMapper categoryAndLabelMapper;

    /**
     * 按照类型查询类别
     * @param type 类型
     * @return 类别列表
     */
    public List<Category> getCategoryByType(int type){
       return categoryAndLabelMapper.selectCategoryByType(type);
    }

    /**
     * 保存类别
     * @param category 类别
     * @return 保存结果
     */
    public int saveCategory(Category category){
        return categoryAndLabelMapper.saveCategory(category);
    }

    /**
     * 保存标签
     * @param label 标签
     * @return 结果
     */
    public int saveLabel(Label label){
        return categoryAndLabelMapper.saveLabel(label);
    }

    /**
     * 查询所有类别
     * @return 类别列表
     */
    public List<Category> getCategory(){
        return categoryAndLabelMapper.selectCategory();
    }


    /**
     * 按照类别id搜索标签
     * @param idCategory 类别id
     * @return 标签列表
     */
    public List<Label> getLabelByCategoryId(int idCategory){
        return categoryAndLabelMapper.selectLabelByCategoryId(idCategory);
    }

    /**
     * 添加文章时更新该标签下文章的数目
     * @param idLabel
     * @return 更新结果
     */
    public int updateLabelForAddArticle(int idLabel){
        return categoryAndLabelMapper.updateLabelForAddArticle(idLabel);
    }

    /**
     * 按照id查询标签
     * @param id id
     * @标签
     */
    public Label getLabelById(int id){
        return categoryAndLabelMapper.selectLabelById(id);
    }

    /**
     * 修改类别
     * @param category 类别
     * @return 结果
     */
    public int updateCategory(Category category){
        return categoryAndLabelMapper.updateCategory(category);
    }

    /**
     * 按照id删除类别
     * @param idCategory id
     * @return 结果
     */
    public int deleteCategoryById(int idCategory){
        return categoryAndLabelMapper.deleteCategoryById(idCategory);
    }

    /**
     * 获取label
     * @return
     */
    public List<Label> getLabel(){
        return categoryAndLabelMapper.selectLabel();
    }

    /**
     * 按照id删除label
     * @param id
     * @return
     */
    public int deleteLabelById(int id){
        return categoryAndLabelMapper.deleteLabelById(id);
    }

    /**
     * 删除文章时减小相应标题下的文章数量
     * @param id 标签id
     */
    public void updateLabelForDeleteArticle(int id){
        categoryAndLabelMapper.updateLabelForDeleteArticle(id);
    }

    /**
     * 获取文章数量最多的前10位标签
     * @return
     */
    public List<Label> getHotLabel(){
        return categoryAndLabelMapper.selectHotLabel();
    }

    /**
     * 更新标签信息
     * @param label
     * @return
     */
    public int updateLabel(Label label){
        return categoryAndLabelMapper.updateLabel(label);
    }
}
