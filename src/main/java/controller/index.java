package controller;

import dao.CategoryAndLabelDAO;
import dao.ShareDAO;
import model.*;
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
    ShareDAO shareDAO = new ShareDAO();
    CategoryAndLabelDAO categoryAndLabelDAO = new CategoryAndLabelDAO();
    @RequestMapping(value = "/index")
    public ModelAndView goIndex(){
        List<Web> webs = shareDAO.getAllWeb();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("webs",webs);
        List<Ebook> ebooks = shareDAO.getAllBook();
        modelAndView.addObject("ebooks",ebooks);
        List<Article> articles = shareDAO.getArticlesForIndex();
        modelAndView.addObject("articles",articles);
        modelAndView.setViewName("page/index.jsp");
        List<Category> categoriesForArticle = categoryAndLabelDAO.getCategoryByType(1);
        List<Category> categoriesForShare = categoryAndLabelDAO.getCategoryByType(2);
        modelAndView.addObject("categoriesForArticle",categoriesForArticle);
        modelAndView.addObject("categoriesForShare",categoriesForShare);
        return modelAndView;
    }
    @RequestMapping(value = "/indexOfAdmin")
    public String goIndexOfAdmin(){
        return "/page/admin/indexOfAdmin.jsp";
    }
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
