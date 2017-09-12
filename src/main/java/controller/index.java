package controller;

import dao.ArticleDAO;
import dao.CategoryAndLabelDAO;
import dao.EbookDAO;
import dao.WebDAO;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by lh
 * on 2017/9/6.
 */
@Controller
public class index {
    private final ArticleDAO articleDAO ;
    private final EbookDAO ebookDAO ;
    private final WebDAO webDAO ;
    private final CategoryAndLabelDAO categoryAndLabelDAO;

    @Autowired
    public index(ArticleDAO articleDAO, EbookDAO ebookDAO, WebDAO webDAO, CategoryAndLabelDAO categoryAndLabelDAO) {
        this.articleDAO = articleDAO;
        this.ebookDAO = ebookDAO;
        this.webDAO = webDAO;
        this.categoryAndLabelDAO = categoryAndLabelDAO;
    }
    /**
     * 主页
     * @return
     */
    @RequestMapping(value = "/index")
    public ModelAndView goIndex(){
        List<Web> webs = webDAO.getWebForIndex();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("webs",webs);
        List<Ebook> ebooks = ebookDAO.getBookForIndex();
        modelAndView.addObject("ebooks",ebooks);
        List<Article> articles = articleDAO.getArticlesForIndex();
        modelAndView.addObject("articles",articles);
        modelAndView.setViewName("page/index.jsp");
        List<Category> categoriesForArticle = categoryAndLabelDAO.getCategoryByType(1);
        List<Category> categoriesForShare = categoryAndLabelDAO.getCategoryByType(2);
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
}
