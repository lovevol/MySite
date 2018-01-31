package controller;

import PagingPlugin.PageParams;
import aoplog.AopLog;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import service.*;
import value.ArticleVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by lh
 * on 2017/9/9.
 */

/**
 * 处理管理员分享的请求
 * @author lh
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    /**
     * service层
     */
    private CategoryAndLabelService categoryAndLabelService;
    private EbookService ebookService;
    private WebService webService;
    private ArticleService articleService;
    private UserService userService;

    /**
     * 自动注入
     * @param categoryAndLabelService
     * @param ebookService
     * @param webService
     * @param articleService
     */
    @Autowired
    public AdminController(CategoryAndLabelService categoryAndLabelService, EbookService ebookService, WebService webService, ArticleService articleService,UserService userService) {
        this.categoryAndLabelService = categoryAndLabelService;
        this.ebookService = ebookService;
        this.webService = webService;
        this.articleService = articleService;
        this.userService = userService;
    }




    /**
     * 添加网站
     *
     * @param web 网站
     * @param request
     * @param response
     * @return 管理员主页地址
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/addWeb")
    @AopLog(bussTypeDesc = "管理员业务",operateTypeDesc = "添加网站")
    public String addWeb(Web web, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (web.getWebUrl() == null || web.getDescription() == null || web.getLabel() == null
                || "".equals(web.getWebUrl()) || "".equals(web.getDescription()) || "".equals(web.getLabel())) {
            request.setAttribute("errorMsg", "添加网站的数据不正确！");
            return "redirect:/admin/indexOfAdmin";
        } else {
            webService.addWeb(web);
            return "redirect:/index";
        }
    }

    /**
     * 添加电子书
     *
     * @param request
     * @param ebook
     * @return 管理员主页地址
     * @throws IOException
     */
    @RequestMapping(value = "/addEbook")
    @AopLog(bussTypeDesc = "管理员业务",operateTypeDesc = "添加电子书")
    public String addEbook(HttpServletRequest request, Ebook ebook) throws IOException {
        if (ebook.getDescription() != null && !"".equals(ebook.getDescription())
                && ebook.getBookName() != null) {
            String path = request.getServletContext().getRealPath("/file/");
            ebook.setPath(path + File.separator + ebook.getBookName());
            ebookService.addEbook(ebook);
            return "redirect:/admin/indexOfAdmin";
        }
        request.setAttribute("errorMsg", "添加书的数据不正确！");
        return "redirect:/admin/indexOfAdmin";
    }

    /**
     * 转向添加文章的页面
     * @return 添加文章页面地址
     */
    @RequestMapping(value = "/goAddArticle")
    public ModelAndView goAddArticel(){
        //获取文章类别
        List<Category> categories = categoryAndLabelService.getCategoryByType(1);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("categories",categories);
        modelAndView.setViewName("/page/admin/addArticle.jsp");
        return modelAndView;
    }
    /**
     * 添加文章
     * @param article
     * @param request
     * @param image 封面图片
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/addArticle")
    @AopLog(bussTypeDesc = "管理员业务",operateTypeDesc = "添加文章")
    public String addArticle(Article article, HttpServletRequest request, MultipartFile image) throws IOException {
        if (article.getTitle() != null && !"".equals(article.getTitle())
                && article.getLabel() != null && !"".equals(article.getLabel())
                && article.getCategory() != null && !"".equals(article.getCategory())
                && article.getContent() != null && !"".equals(article.getContent())) {
            if (image != null && !image.isEmpty()) {
                //String path = "/home/mysite_image/";
                String path = request.getServletContext().getRealPath("/image/");
                String imageName = image.getOriginalFilename();
                //文件重命名，解决中文文件名问题
                imageName = UUID.randomUUID().toString()+imageName.substring(imageName.lastIndexOf("."));
                File filePath = new File(path, imageName);
                if (!filePath.getParentFile().exists()) {
                    filePath.getParentFile().mkdirs();
                }
                image.transferTo(new File(path + File.separator + imageName));
                article.setImagePath(imageName);
            }
            article.setDate(new Date(System.currentTimeMillis()));
            articleService.addArticle(article);
            return "redirect:/admin/indexOfAdmin";
        } else {
            request.setAttribute("errorMsg", "添加文章数据不正确！");
            return "redirect:/admin/goAddArticle";
        }
    }

    /**
     * 异步的方式处理文件的上传
     */
    @RequestMapping(value = "/uploadEbookFile")
    @AopLog(bussTypeDesc = "管理员业务",operateTypeDesc = "上传电子书")
    public String uploadEbookFile(@RequestParam("file") MultipartFile file,
                                  HttpServletRequest request,HttpServletResponse response) throws IOException {
        if (file != null && !file.isEmpty()) {
            String path = request.getServletContext().getRealPath("/file/");
            String fileName = file.getOriginalFilename();
            File filePath = new File(path, fileName);
            if (!filePath.getParentFile().exists()) {
                filePath.getParentFile().mkdirs();
            }
            file.transferTo(new File(path + File.separator + fileName));
            response.getWriter().write(fileName);
        }
        return null;
    }

    /**
     * 添加类别
     * @param category
     * @return
     */
    @RequestMapping(value = "/addCategory")
    @AopLog(bussTypeDesc = "管理员业务",operateTypeDesc = "添加类别")
    public String addCategory(Category category){
        categoryAndLabelService.addCategory(category);
        return "redirect:/admin/indexOfAdmin";
    }

    /**
     * 添加标签
     * @param label
     * @return
     */
    @RequestMapping(value = "/addLabel")
    @AopLog(bussTypeDesc = "管理员业务",operateTypeDesc = "添加标签")
    public String addLabel(Label label){
        label.setArticleNum(0);
        categoryAndLabelService.addLabel(label);
        return "redirect:/admin/indexOfAdmin";
    }
    /**
     * 管理员的主页
     * @return
     */
    @RequestMapping(value = "/indexOfAdmin")
    public String goIndexOfAdmin(){
        return "/page/admin/indexOfAdmin.jsp";
    }

    /**
     * 去添加标签界面
     * @return
     */
    @RequestMapping(value = "/goAddLabel")
    public ModelAndView gpAddLabel(){
       List<Label> labels = categoryAndLabelService.getLabel();
       ModelAndView modelAndView = new ModelAndView();
       modelAndView.addObject("labels",labels);
        List<Category> categories = categoryAndLabelService.getCategory();
        modelAndView.addObject("categories",categories);
        modelAndView.setViewName("/page/admin/addLabel.jsp");
        return modelAndView;
    }


    /**
     * 转向添加类别界面
     * @return
     */
    @RequestMapping(value = "/goAddCategory")
    public ModelAndView goAddCategory(){
        List<Category> categories = categoryAndLabelService.getCategory();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("categories",categories);
        modelAndView.setViewName("/page/admin/addCategory.jsp");
        return modelAndView;
    }

    /**
     * 类别修改
     * @param category
     * @return
     */
    @RequestMapping(value = "/modifyCategory")
    @AopLog(bussTypeDesc = "管理员业务",operateTypeDesc = "修改类别")
    public ModelAndView modifyCategory(Category category){
        int result = categoryAndLabelService.updateCategory(category);
        ModelAndView modelAndView =new ModelAndView();
        if (result >= 1){
            modelAndView.setViewName("redirect:/admin/goAddCategory");
        }
        return modelAndView;
    }

    /**
     * 按照id删除类别
     * @param idCategory
     * @return
     */
    @RequestMapping(value = "/deleteCategoryById")
    @AopLog(bussTypeDesc = "管理员业务",operateTypeDesc = "删除类别")
    public ModelAndView deleteCategoryById(int idCategory){
        int result = categoryAndLabelService.deleteCategoryById(idCategory);
        ModelAndView modelAndView =new ModelAndView();
        if (result >= 1){
            modelAndView.setViewName("redirect:/admin/goAddCategory");
        }
        return modelAndView;
    }

    /**
     * 按照id删除label
     * @param idLabel
     * @return
     */
    @RequestMapping(value = "/deleteLabelById")
    @AopLog(bussTypeDesc = "管理员业务",operateTypeDesc = "删除标签")
    public ModelAndView deleteLabelById(int idLabel){
        ModelAndView modelAndView = new ModelAndView();
        int result = categoryAndLabelService.deleteLabelById(idLabel);
        if (result >= 1){
            modelAndView.setViewName("redirect:/admin/goAddLabel");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/modifyLabel")
    @AopLog(bussTypeDesc = "管理员业务",operateTypeDesc = "修改标签")
    public ModelAndView modifyLabel(Label label){
        int result = categoryAndLabelService.updateLabel(label);
        ModelAndView modelAndView =new ModelAndView();
        if (result >= 1){
            modelAndView.setViewName("redirect:/admin/goAddLabel");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/getUser")
    public ModelAndView getUser(){
        //分页插件测试
        /*PageParams pageParams = new PageParams();
        pageParams.setUseFlag(true);
        pageParams.setCheckFlag(true);
        pageParams.setPageSize(20);*/
        ModelAndView modelAndView = new ModelAndView();
        /*modelAndView.addObject("users",userService.getUSer(pageParams));*/
        modelAndView.setViewName("/page/admin/user.jsp");
        return modelAndView;
    }

    @RequestMapping(value = "/goArticleList")
    public ModelAndView goArticleList(ArticleVO articleVO,PageParams pageParams){
        ModelAndView md = new ModelAndView();
        List<Article> articles = articleService.getArticle(articleVO);
        md.addObject("articles",articles);
        List<Category> categoriesForArticle = categoryAndLabelService.getCategoryByType(1);
        md.addObject("categoriesForArticle",categoriesForArticle);
        md.setViewName("/page/admin/articleListForEdit.jsp");
        return md;
    }

    @RequestMapping(value = "/goArticleListByCategory")
    public ModelAndView goArticleListByCategory(int idCategory){
        ModelAndView md = new ModelAndView();
        List<Article> articles = articleService.getArticleByCategory(idCategory);
        md.addObject("articles",articles);
        List<Category> categoriesForArticle = categoryAndLabelService.getCategoryByType(1);
        md.addObject("categoriesForArticle",categoriesForArticle);
        md.setViewName("/page/admin/articleListForEdit.jsp");
        return md;
    }

    @RequestMapping(value = "/deleteArticleById")
    @AopLog(bussTypeDesc = "管理员业务",operateTypeDesc = "删除文章")
    public ModelAndView deleteArticleById(int id){
        int result = articleService.deleteArticleById(id);
        ModelAndView md = new ModelAndView();

        if (result < 1){
            md.addObject("errorMsg","删除失败");
        }
        md.setViewName("redirect:/admin/goArticleList");
        return md;
    }

    @RequestMapping(value = "/goUpdateArticle")
    public ModelAndView goUpdateArticle(int id){
        ModelAndView md = new ModelAndView();
        Article article = articleService.getArticleById(id);
        md.addObject("article",article);
        //获取类别
        List<Category> categories = categoryAndLabelService.getCategoryByType(1);
        md.addObject("categories",categories);
        md.setViewName("/page/admin/updateArticle.jsp");
        return md;
    }

    @RequestMapping(value = "/updateArticle")
    @AopLog(bussTypeDesc = "管理员业务",operateTypeDesc = "更新文章")
    public String updateArticle(Article article, HttpServletRequest request, MultipartFile image) throws IOException {
        if (article.getTitle() != null && !"".equals(article.getTitle())
                && article.getLabel() != null && !"".equals(article.getLabel())
                && article.getCategory() != null && !"".equals(article.getCategory())
                && article.getContent() != null && !"".equals(article.getContent())) {
            if (image != null && !image.isEmpty()) {
                //String path = "/home/mysite_image/";
                String path = request.getServletContext().getRealPath("/image/");
                String imageName = image.getOriginalFilename();
                //文件重命名，解决中文文件名问题
                imageName = UUID.randomUUID().toString()+imageName.substring(imageName.lastIndexOf("."));
                File filePath = new File(path, imageName);
                if (!filePath.getParentFile().exists()) {
                    filePath.getParentFile().mkdirs();
                }
                image.transferTo(new File(path + File.separator + imageName));
                article.setImagePath(imageName);
            }
            article.setDate(new Date(System.currentTimeMillis()));
            articleService.updateArticle(article);
            request.setAttribute("errorMsg", "修改成功！");
            return "redirect:/admin/goArticleList";
        } else {
            request.setAttribute("errorMsg", "添加文章数据不正确！");
            return "redirect:/admin/goArticleList";
        }
    }

    @RequestMapping(value = "/goWebEdit")
    public ModelAndView goWebEdit(){
        ModelAndView md = new ModelAndView();
        List<Web> webs = webService.getWeb();
        md.addObject("webs",webs);
        md.setViewName("/page/admin/webListForEdit.jsp");
        return md;
    }

    @RequestMapping(value = "/deleteWebById")
    @ResponseBody
    @AopLog(bussTypeDesc = "管理员业务",operateTypeDesc = "删除网站")
    public String deleteWebById(int idWeb){
        int result = webService.deleteWebById(idWeb);
        if (result >= 1){
            return "success";
        }else {
            return "error";
        }
    }

    @RequestMapping(value = "/goEbookEdit")
    public ModelAndView goEbookEdit(){
        ModelAndView md = new ModelAndView();
        List<Ebook> ebooks = ebookService.getEbook();
        md.addObject("ebooks",ebooks);
        md.setViewName("/page/admin/ebookForEdit.jsp");
        return md;
    }

    @RequestMapping(value = "/deleteEbookById")
    @ResponseBody
    @AopLog(bussTypeDesc = "管理员业务",operateTypeDesc = "删除电子书")
    public String deleteEbookById(int idEbook){
        int result = ebookService.deleteEbookById(idEbook);
        if (result >= 1){
            return "success";
        }else {
            return "error";
        }
    }
}
