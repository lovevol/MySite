package controller;

import dao.ArticleDAO;
import dao.EbookDAO;
import dao.WebDAO;
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
import service.ArticleService;
import service.CategoryAndLabelService;
import service.EbookService;
import service.WebService;
import valueobject.ArticleVO;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

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

    @Autowired
    public ShareController(ArticleService articleService, EbookService ebookService, WebService webService,CategoryAndLabelService categoryAndLabelService) {
        this.articleService = articleService;
        this.ebookService = ebookService;
        this.webService = webService;
        this.categoryAndLabelService = categoryAndLabelService;
    }

    /**
     * 跳转到网站分享页面
     * @return
     */
    @RequestMapping(value = "/webShare")
    public ModelAndView webShare(){
        //List<Web> webs = webService.getWebForIndex();
        ModelAndView modelAndView = new ModelAndView();
        //modelAndView.addObject("webs",webs);
        modelAndView.setViewName("/page/share/webShare.jsp");
        return modelAndView;
    }

    /**
     * 跳转到电子书分享界面
     * @return
     */
    @RequestMapping(value = "ebookShare")
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
    public ModelAndView readArticle(int articleId){
        Article article = articleService.getArticleById(articleId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("article",article);
        List<Category> categoriesForArticle = categoryAndLabelService.getCategoryByType(1);
        modelAndView.addObject("categoriesForArticle",categoriesForArticle);
        List<Label> labels = categoryAndLabelService.getLabel();
        modelAndView.addObject("labels",labels);
        modelAndView.setViewName("/page/share/readArticle.jsp");
        return modelAndView;
    }

    @RequestMapping(value = "/searchArticle")
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
}
