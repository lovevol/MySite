package controller;

import dao.ShareDAO;
import model.Article;
import model.Ebook;
import model.Web;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

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
    private ShareDAO shareDAO = new ShareDAO();
    private String ebookFileName;

    /**
     * 添加网站
     *
     * @param web
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
            return "/indexOfAdmin";
        } else {
            shareDAO.addWeb(web);
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
    @RequestMapping(value = "addEbook")
    public String addEbook(HttpServletRequest request, Ebook ebook) throws IOException {
        if (ebook.getDescription() != null && !"".equals(ebook.getDescription())
                && ebookFileName != null) {
            String path = request.getServletContext().getRealPath("/file/");
            ebook.setPath(path + File.separator + ebookFileName);
            ebook.setBookName(ebookFileName);
            shareDAO.addEbook(ebook);
            return "redirect:/index";
        }
        request.setAttribute("errorMsg", "添加书的数据不正确！");
        return "/page/admin/indexOfAdmin.jsp";
    }

    @RequestMapping(value = "/addArticle")
    public String addArticle(Article article, HttpServletRequest request, MultipartFile image) throws IOException {
        if (article.getTitle() != null && !"".equals(article.getTitle())
                && article.getLabel() != null && !"".equals(article.getLabel())
                && article.getCategory() != null && !"".equals(article.getCategory())
                && article.getContent() != null && !"".equals(article.getContent())) {
            if (image != null && !image.isEmpty()) {
                String path = request.getServletContext().getRealPath("/image/");
                String imageName = image.getOriginalFilename();
                File filePath = new File(path, imageName);
                if (!filePath.getParentFile().exists()) {
                    filePath.getParentFile().mkdirs();
                }
                image.transferTo(new File(path + File.separator + imageName));
                article.setImagePath(imageName);
            }
            article.setDate(new Timestamp(System.currentTimeMillis()));
            shareDAO.addArticle(article);
            return "redirect:/index";
        } else {
            request.setAttribute("errorMsg", "添加文章数据不正确！");
            return "/page/admin/addArticle.jsp";
        }
    }

    /**
     * 异步的方式处理文件的上传
     */
    @RequestMapping(value = "/uploadEbookFile")
    public String uploadEbookFile(@RequestParam("file") MultipartFile file,
                                  HttpServletRequest request) throws IOException {
        if (file != null && !file.isEmpty()) {
            String path = request.getServletContext().getRealPath("/file/");
            String fileName = file.getOriginalFilename();
            ebookFileName = fileName;
            File filePath = new File(path, fileName);
            if (!filePath.getParentFile().exists()) {
                filePath.getParentFile().mkdirs();
            }
            file.transferTo(new File(path + File.separator + fileName));

        }
        return "/page/admin/indexOfAdmin.jsp";
    }
}
