package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lh
 * on 2017/9/6.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    /**
     * 用户界面的路径
     */
    private final String PAGE_PATH = "/page/user/";
    /**
     * 登陆的具体路径
     */
    private final String LOGIN_PAGE = PAGE_PATH + "login.jsp";

    /**
     * 跳转到用户登陆界面
     * @return 界面路径
     */
    @RequestMapping(value = "/login")
    public String login(){
        return LOGIN_PAGE;
    }
}
