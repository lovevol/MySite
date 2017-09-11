package dao;

import Utils.MyBatisUtil;
import model.Category;
import model.Label;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by lh
 * on 2017/9/10.
 */
@Component
public class CategoryAndLabelDAO {
    private SqlSession sqlSession = MyBatisUtil.getSqlSession();
    public List<Category> getCategoryByType(int type){
        List<Category> categories = sqlSession.selectList("mapper.CategoryAndLabelMapper.selectCategoryByType",
                type);
        sqlSession.commit();
        return categories;
    }
    public List<Label> getLabelByCategory(int category){
        List<Label> labels = sqlSession.selectList("mapper.CategoryAndLabelMapper.selectLabelByCategory",
                category);
        sqlSession.commit();
        return labels;
    }
}
