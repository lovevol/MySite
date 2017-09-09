package controller;

import dao.ShareDAO;
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

/**
 * Created by lh
 * on 2017/9/9.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private ShareDAO shareDAO = new ShareDAO();
    @RequestMapping(value = "/addWeb")
    public String addWeb(Web web, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(web.getWebUrl() == null || web.getDescription() == null || web.getLabel() == null
                ||"".equals(web.getWebUrl()) || "".equals(web.getDescription()) || "".equals(web.getLabel())){
            request.setAttribute("errorMsg","添加网站的数据不正确！");
            return "/indexOfAdmin";
        }else {
            shareDAO.addWeb(web);
            return "/page/admin/indexOfAdmin.jsp";
        }
    }
    @RequestMapping(value = "addEbook")
    public String addEbook(HttpServletRequest request, Ebook ebook,
                           @RequestParam("file") MultipartFile file) throws IOException {
        if(!file.isEmpty()){
            String path = request.getServletContext().getRealPath("/file/");
            String fileName = file.getOriginalFilename();
            File filePath = new File(path,fileName);
            if(!filePath.getParentFile().exists()){
                filePath.getParentFile().mkdirs();
            }
            file.transferTo(new File(path+File.separator+fileName));
            ebook.setPath(path+File.separator+fileName);
            ebook.setBookName(fileName);
            shareDAO.addEbook(ebook);
            return "/page/admin/indexOfAdmin.jsp";
        }
        request.setAttribute("errorMsg","添加书的数据不正确！");
        return "/page/admin/indexOfAdmin.jsp";
    }
}
