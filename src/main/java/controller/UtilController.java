package controller;

import aoplog.AopLog;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import redis.JedisService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/other")
public class UtilController {
    @Autowired
    private JedisService jedisService;
    @RequestMapping(value = "/history")
    @AopLog(bussTypeDesc = "其他业务",operateTypeDesc = "获取浏览记录")
    public ModelAndView getBrowsingHistory(HttpSession session){
        ModelAndView md = new ModelAndView();
        User user = (User) session.getAttribute("user");
        List<String> histories = jedisService.getRecordForBrowsing(user);
        md.addObject("histories",histories);
        md.setViewName("/page/user/browsingHistory.jsp");
        return md;
    }
}
