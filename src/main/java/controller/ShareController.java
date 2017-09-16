package controller;

import dao.ArticleDAO;
import dao.EbookDAO;
import dao.WebDAO;
import model.Article;
import model.Ebook;
import model.Web;
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

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by lh
 * on 2017/9/9.
 */
@Controller
@RequestMapping(value = "/share")
public class ShareController {
    private final ArticleDAO articleDAO;
    private final EbookDAO ebookDAO;
    private final WebDAO webDAO;

    @Autowired
    public ShareController(ArticleDAO articleDAO, EbookDAO ebookDAO, WebDAO webDAO) {
        this.articleDAO = articleDAO;
        this.ebookDAO = ebookDAO;
        this.webDAO = webDAO;
    }

    /**
     * 跳转到网站分享页面
     * @return
     */
    @RequestMapping(value = "/webShare")
    public ModelAndView webShare(){
        List<Web> webs = webDAO.getWebForIndex();
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
    public ModelAndView ebookShare(){
        List<Ebook> ebooks = ebookDAO.getEbookForIndex();
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
    @RequestMapping(value = "readArticle")
    public ModelAndView readArticle(int articleId){
        Article article = articleDAO.getArticleById(articleId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("article",article);
        modelAndView.setViewName("/page/share/readArticle.jsp");
        return modelAndView;
    }
}
