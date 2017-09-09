package controller;

import dao.ShareDAO;
import model.Ebook;
import model.Web;
import org.apache.commons.io.FileUtils;
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
    ShareDAO shareDAO = new ShareDAO();
    @RequestMapping(value = "/webShare")
    public ModelAndView webShare(){
        List<Web> webs = shareDAO.getAllWeb();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("webs",webs);
        modelAndView.setViewName("/page/share/webShare.jsp");
        return modelAndView;
    }
    @RequestMapping(value = "ebookShare")
    public ModelAndView ebookShare(){
        List<Ebook> ebooks = shareDAO.getAllBook();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("ebooks",ebooks);
        modelAndView.setViewName("/page/share/ebookShare.jsp");
        return modelAndView;
    }
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
}
