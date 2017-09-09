package controller;

import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by lh
 * on 2017/9/6.
 */
@Controller
public class index {
    @RequestMapping(value = "/index")
    public String goIndex(){
        return "page/index.jsp";
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
            return "/index";
        }
    }
}
