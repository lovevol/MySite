package controller;

import aoplog.AopLog;
import model.*;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import redis.JedisService;
import service.ArticleService;
import service.CategoryAndLabelService;
import service.EbookService;
import service.WebService;
import value.ArticleVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Created by lh
 * on 2017/9/9.
 * @author lh
 */
@Controller
@RequestMapping(value = "/share")
public class ShareController {

    private ArticleService articleService;
    private EbookService ebookService;
    private WebService webService;
    private CategoryAndLabelService categoryAndLabelService;
    private JedisService jedisService;

    @Autowired
    public ShareController(ArticleService articleService, EbookService ebookService, WebService webService,CategoryAndLabelService categoryAndLabelService,
                           JedisService jedisService) {
        this.articleService = articleService;
        this.ebookService = ebookService;
        this.webService = webService;
        this.categoryAndLabelService = categoryAndLabelService;
        this.jedisService = jedisService;
    }

    /**
     * 跳转到网站分享页面
     * @return
     */
    @RequestMapping(value = "/webShare")
    @AopLog(bussTypeDesc = "分享业务",operateTypeDesc = "查看网站")
    public ModelAndView webShare(){
        List<Web> webs = webService.getWeb();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("webs",webs);
        modelAndView.setViewName("/page/share/webShare.jsp");
        return modelAndView;
    }

    /**
     * 跳转到电子书分享界面
     * @return
     */
    @RequestMapping(value = "ebookShare")
    @AopLog(bussTypeDesc = "分享业务",operateTypeDesc = "查看电子书")
    public ModelAndView ebookShare(){
        List<Ebook> ebooks = ebookService.getEbook();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("ebooks",ebooks);
        modelAndView.setViewName("/page/share/ebookShare.jsp");
        return modelAndView;
    }

    /**
     * 下载电子书
     * @param request
     * @param fileName
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/downloadEbook")
    @AopLog(bussTypeDesc = "分享业务",operateTypeDesc = "下载电子书")
    public ResponseEntity<byte[]> downloadEbook(HttpServletRequest request,
                                                @RequestParam("fileName") String fileName,
                                                Model model) throws IOException {
        String path = request.getServletContext().getRealPath("/file/");
        File file = new File(path+File.separator + fileName);
        String fileDownloadName = new String(fileName.getBytes("UTF-8"),"iso-8859-1");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentDispositionFormData("attachment",fileDownloadName);
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),httpHeaders, HttpStatus.CREATED);
    }

    /**
     * 读文章
     * @param articleId
     * @return
     */
    @RequestMapping(value = "/readArticle")
    @AopLog(bussTypeDesc = "分享业务",operateTypeDesc = "读文章")
    public ModelAndView readArticle(int articleId, HttpSession session){
        Article article = articleService.getArticleById(articleId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("article",article);
        List<Category> categoriesForArticle = categoryAndLabelService.getCategoryByType(1);
        modelAndView.addObject("categoriesForArticle",categoriesForArticle);
        List<Label> labels = categoryAndLabelService.getLabelByCategoryId(article.getCategory().getIdCategory());
        modelAndView.addObject("labels",labels);
        //记录阅读量
        jedisService.setViewNumOfArticle(article);
        String num = jedisService.getViewNumOfArticle(article);
        modelAndView.addObject("num",num);
        //查看用户是否收藏该文章
        User user = (User)session.getAttribute("user");
        if (user != null){
            Set<String> ids = jedisService.getArticleForUser(user.getIdUser());
            if (ids.contains(String.valueOf(articleId))){
                modelAndView.addObject("saved","true");
            }
        }
        modelAndView.setViewName("/page/share/readArticle.jsp");
        return modelAndView;
    }

    @RequestMapping(value = "/searchArticle")
    @AopLog(bussTypeDesc = "分享业务",operateTypeDesc = "搜索文章")
    public ModelAndView searchArticle(ArticleVO articleVO){
        ModelAndView modelAndView = new ModelAndView();
        List<Article> articles = articleService.getArticle(articleVO);
        modelAndView.addObject("articles",articles);
        List<Category> categoriesForArticle = categoryAndLabelService.getCategoryByType(1);
        modelAndView.addObject("categoriesForArticle",categoriesForArticle);
        modelAndView.setViewName("/page/share/articleShare.jsp");
        List<Category> categories = categoryAndLabelService.getCategory();
        modelAndView.addObject("categorys",categories);
        modelAndView.addObject("articleVO",articleVO);
        return modelAndView;
    }

    /**
     * 按照类别获取文章
     * @return
     */
    @RequestMapping(value = "/getArticleByCategory")
    @AopLog(bussTypeDesc = "分享业务",operateTypeDesc = "按照类别获取文章")
    public ModelAndView getArticleByCategory(int idCategory){
        ModelAndView modelAndView = new ModelAndView();
        List<Article> articles = articleService.getArticleByCategory(idCategory);
        modelAndView.addObject("articles",articles);
        List<Category> categoriesForArticle = categoryAndLabelService.getCategoryByType(1);
        modelAndView.addObject("categoriesForArticle",categoriesForArticle);
        modelAndView.setViewName("/page/share/articleShare.jsp");
        return modelAndView;
    }
}
