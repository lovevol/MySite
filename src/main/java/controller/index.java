package controller;

import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.ArticleService;
import service.CategoryAndLabelService;
import service.EbookService;
import service.WebService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by lh
 * on 2017/9/6.
 */
@Controller
public class index {
    /**
     * service
     */
    private CategoryAndLabelService categoryAndLabelService;
    private EbookService ebookService;
    private WebService webService;
    private ArticleService articleService;

    /**
     * 自动注入服务层
     * @param categoryAndLabelService
     * @param ebookService
     * @param webService
     * @param articleService
     */
    @Autowired
    public index(CategoryAndLabelService categoryAndLabelService, EbookService ebookService, WebService webService, ArticleService articleService) {
        this.categoryAndLabelService = categoryAndLabelService;
        this.ebookService = ebookService;
        this.webService = webService;
        this.articleService = articleService;
    }

    /**
     * 主页
     * @return 主页地址
     */
    @RequestMapping(value = "/index")
    public ModelAndView goIndex(String keyWord){
        //获取主页的网站分享
        List<Web> webs = webService.getWebForIndex(keyWord);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("webs",webs);

        //获取主页的电子书分享
        List<Ebook> ebooks = ebookService.getEbookForIndex(keyWord);
        modelAndView.addObject("ebooks",ebooks);

        //获取主页的文章分享
        List<Article> articles = articleService.getArticleForIndex(keyWord);
        modelAndView.addObject("articles",articles);
        modelAndView.setViewName("page/index.jsp");

        //获取分类和标签数据
        List<Category> categoriesForArticle = categoryAndLabelService.getCategoryByType(1);
        List<Category> categoriesForShare = categoryAndLabelService.getCategoryByType(2);
        modelAndView.addObject("categoriesForArticle",categoriesForArticle);
        modelAndView.addObject("categoriesForShare",categoriesForShare);
        return modelAndView;
    }


    /**
     * 管理员验证
     * @param request
     * @param user
     * @return
     */
    @RequestMapping(value = "/verifyAdmin")
    public String verify(HttpServletRequest request, User user){
        HttpSession session = request.getSession();
        session.setAttribute("adminVerify","success");
        if ("lh".equals(user.getLoginName()) && "123456".equals(user.getPassword())){
            return "/page/admin/indexOfAdmin.jsp";
        }else {
            return "redirect:/index";
        }
    }

    /**
     * 动态获取某一类别下的标签信息
     * 实现类别和标签的联动
     * @param idCategory
     * @return
     */
    @RequestMapping(value = "/getLabelForAjax")
    @ResponseBody
    public Object getLabelForAjax(int idCategory){
        List<Label> labels = categoryAndLabelService.getLabelByCategoryId(idCategory);
        return labels;
    }
}
