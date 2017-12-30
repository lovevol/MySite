package controller;

import aoplog.AopLog;
import model.User;
import myenum.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by lh
 * on 2017/9/6.
 * @author lh
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;
    /**
     * 用户界面的路径
     */
    private final String PAGE_PATH = "/page/user/";
    /**
     * 登陆的具体路径
     */
    private final String LOGIN_PAGE = PAGE_PATH + "login.jsp";
    private final String REGISTER_PAGE = PAGE_PATH +"register.jsp";
    /**
     * 跳转到用户登陆界面
     * @return 界面路径
     */
    @AopLog(operateTypeDesc = "用户登陆界面调转")
    @RequestMapping(value = "/login")
    public String login(){
        return LOGIN_PAGE;
    }

    @AopLog(operateTypeDesc = "用户登陆验证")
    @RequestMapping(value = "/validate")
    public String validate(HttpServletRequest request, User user){
        boolean validate = userService.validateLogin(user);
        if (validate && user.getRoleType() == 1){
            HttpSession session = request.getSession();
            user = userService.getUserByLoginName(user.getLoginName());
            session.setAttribute("user",user);
            return "redirect:/index";
        }else if (validate && user.getRoleType() == 2){
            user = userService.getUserByLoginName(user.getLoginName());
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
            return "redirect:/admin/indexOfAdmin";
        }else {
            return "redirect:/user/login";
        }
    }

    /**
     * 退出登陆
     * @param request
     * @return
     */
    @AopLog(operateTypeDesc = "退出登陆")
    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/user/login";
    }

    /**
     * 异步验证用户名是否存在
     * @param response
     * @param loginName
     * @return
     */
    @RequestMapping(value = "/validateLoginName")
    public String validateLoginName(HttpServletResponse response,String loginName){
        User user = userService.getUserByLoginName(loginName);
        if (user != null){
            try {
                response.getWriter().write("false");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                response.getWriter().write("true");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 注册并登陆
     * @param request
     * @param user
     * @return
     */
    @RequestMapping(value = "/register")
    public String register(HttpServletRequest request,User user,int genderId){
        user.setGender(Gender.getGender(genderId));
        userService.registerUser(user);
        //HttpSession session = request.getSession();
        //session.setAttribute("user",user);
        return "redirect:/index";
    }

    /**
     * 跳转到注册界面
     * @return
     */
    @RequestMapping(value = "/registerForm")
    public String registerForm(){
        return REGISTER_PAGE;
    }

    /**
     * 用户邮箱验证
     * @param user
     * @return
     */
    @RequestMapping(value = "/validateUserForRegister")
    public String validateUser(User user){

       if (userService.updateUserByIdAndValidateCode(user)){
           return "redirect:/user/login";
       }else {
           return "redirect:/user/register";
       }

    }
}
