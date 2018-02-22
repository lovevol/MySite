package controller;

import aoplog.AopLog;
import model.Article;
import model.User;
import myenum.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import redis.JedisService;
import service.ArticleService;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Set;

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
    @Autowired
    private JedisService jedisService;
    @Autowired
    private ArticleService articleService;
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
    public ModelAndView validate(HttpServletRequest request, User user){
        ModelAndView md = new ModelAndView();
        HttpSession session = request.getSession();
        boolean validate = userService.validateLogin(user);
        user = userService.getUserByLoginName(user.getLoginName());
        if(user != null){
            //用户状态判断
            if (!(1==user.getStatus())){
                md.addObject("loginName",user.getLoginName());
                md.setViewName("/page/user/activeUser.jsp");
            }else if (validate && user.getRoleType() == 1){
                session.setAttribute("user",user);
                md.setViewName("redirect:/index");
            }else if (validate && user.getRoleType() == 2){
                session.setAttribute("user",user);
                md.setViewName("redirect:/admin/indexOfAdmin");
            }else {
                md.addObject("errorMsg","请输入正确的登录名和密码！");
                md.setViewName("forward:/user/login");
            }
        }else {
            md.addObject("errorMsg","请输入正确的登录名和密码！");
            md.setViewName("forward:/user/login");
        }

        return md;
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
    public ModelAndView register(HttpServletRequest request,User user,int genderId){
        user.setGender(Gender.getGender(genderId));
        userService.registerUser(user);
        ModelAndView md = new ModelAndView();
        md.addObject("loginName",user.getLoginName());
        md.setViewName("/page/user/activeUser.jsp");
        //HttpSession session = request.getSession();
        //session.setAttribute("user",user);
        return md;
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
    @AopLog(bussTypeDesc = "用户业务",operateTypeDesc = "用户激活")
    public String validateUser(User user){

       if (userService.updateUserByIdAndValidateCode(user)){
           return "redirect:/user/login";
       }else {
           return "redirect:/user/register";
       }

    }

    /**
     * 查看个人信息
     * @param session
     * @return
     */
    @RequestMapping(value = "/myInfo")
    @AopLog(bussTypeDesc = "用户业务",operateTypeDesc = "查看个人信息")
    public ModelAndView myInfo(HttpSession session){
        User user = (User) session.getAttribute("user");

        ModelAndView md = new ModelAndView();
        if (user != null){
            //获取收藏的文章
            Set<String> ids = jedisService.getArticleForUser(user.getIdUser());
            if (ids.size() >0){
                List<Article> articles =articleService.selectArticleForUserSave(ids);
                md.addObject("articles",articles);
            }
            md.addObject("user",user);
            md.setViewName("/page/user/myInfo.jsp");
        }else {
            md.setViewName("redirect:/user/login");
        }

        return md;
    }

    /**
     * 用户激活
     * @param user
     * @return
     */
    @AopLog(bussTypeDesc = "用户业务",operateTypeDesc = "用户激活")
    @RequestMapping(value = "/activeUser")
    public ModelAndView activeUser(User user){
        ModelAndView md = new ModelAndView();
        boolean result = userService.activeUser(user);
        if (result == false){
            md.addObject("errorMsg","验证码错误！");
        }else {
            md.addObject("errorMsg","激活成功，请登陆");
        }
        md.setViewName("forward:/user/login");
        return md;
    }

    /**
     * 文章收藏
     * @param session
     * @param articleId
     * @return
     */
    @RequestMapping(value = "/saveArticleByAjax")
    @ResponseBody
    @AopLog(bussTypeDesc = "用户业务",operateTypeDesc = "收藏文章")
    public Object saveArticleByAjax(HttpSession session,int articleId){
        User user = (User)session.getAttribute("user");
        if (user == null){
            return "false";
        }else {
            jedisService.saveArticleForUser(user.getIdUser(),articleId);
            return "true";
        }
    }

    /**
     * 文章收藏取消
     * @param session
     * @param articleId
     * @return
     */
    @RequestMapping(value = "/cancelArticleByAjax")
    @ResponseBody
    @AopLog(bussTypeDesc = "用户业务",operateTypeDesc = "取消文章收藏")
    public Object cancelArticleByAjax(HttpSession session,int articleId){
        User user = (User)session.getAttribute("user");
        if (user == null){
            return "false";
        }else {
            Long result = jedisService.cancelArticleSave(user.getIdUser(),articleId);
            if (result != 1L){
                return "false";
            }
            return "true";
        }
    }

    /**
     * 密码修改
     * @param session
     * @param user
     * @return
     */
    @AopLog(bussTypeDesc = "用户业务",operateTypeDesc = "密码修改")
    @RequestMapping(value = "/changePassword")
    public ModelAndView changePassword(HttpSession session,User user){
        ModelAndView modelAndView = new ModelAndView();
        boolean result = userService.changePassword(user);
        if (result){
            modelAndView.setViewName("redirect:/user/login");
        }else {
            modelAndView.setViewName("/page/user/changePassword.jsp");
        }
        return modelAndView;
    }

    /**
     * 密码修改时发送验证码
     * @param user
     * @return
     */
    @RequestMapping(value = "/sendValidateCoedByAjax")
    @ResponseBody
    @AopLog(bussTypeDesc = "用户业务",operateTypeDesc = "发送修改密码的验证码")
    public Object sendValidateCoedForChangePassword(User user){

        boolean result = false;
        try {
            result = userService.sendValidateCoedForChangePassword(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result ? "true":"false";
    }
}
