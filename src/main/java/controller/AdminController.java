package controller;

import model.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import service.ArticleService;
import service.CategoryAndLabelService;
import service.EbookService;
import service.WebService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

/**
 * Created by lh
 * on 2017/9/9.
 */

/**
 * 处理管理员分享的请求
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private Logger logger = Logger.getLogger(this.getClass());
    /**
     * service层
     */
    private CategoryAndLabelService categoryAndLabelService;
    private EbookService ebookService;
    private WebService webService;
    private ArticleService articleService;

    /**
     * 自动注入
     * @param categoryAndLabelService
     * @param ebookService
     * @param webService
     * @param articleService
     */
    @Autowired
    public AdminController(CategoryAndLabelService categoryAndLabelService, EbookService ebookService, WebService webService, ArticleService articleService) {
        this.categoryAndLabelService = categoryAndLabelService;
        this.ebookService = ebookService;
        this.webService = webService;
        this.articleService = articleService;
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
    public String addArticle(Article article, HttpServletRequest request, MultipartFile image) throws IOException {
        if (article.getTitle() != null && !"".equals(article.getTitle())
                && article.getLabel() != null && !"".equals(article.getLabel())
                && article.getCategory() != null && !"".equals(article.getCategory())
                && article.getContent() != null && !"".equals(article.getContent())) {
            if (image != null && !image.isEmpty()) {
                String path = request.getServletContext().getRealPath("/image/");
                String imageName = image.getOriginalFilename();
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
        List<Category> categories = categoryAndLabelService.getCategory();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("categorys",categories);
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

    @RequestMapping(value = "/modifyCategory")
    public ModelAndView modifyCategory(Category category){
        int result = categoryAndLabelService.updateCategory(category);
        ModelAndView modelAndView =new ModelAndView();
        if (result >= 1){
            modelAndView.setViewName("redirect:/admin/goAddCategory");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/deleteCategoryById")
    public ModelAndView deleteCategoryById(int idCategory){
        int result = categoryAndLabelService.deleteCategoryById(idCategory);
        ModelAndView modelAndView =new ModelAndView();
        if (result >= 1){
            modelAndView.setViewName("redirect:/admin/goAddCategory");
        }
        return modelAndView;
    }
}
