package Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by lh
 * on 2017/9/9.
 */
public class AdminAuthorizationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Boolean flag = false;
        HttpSession session = request.getSession();
        String adminVerify = (String) session.getAttribute("adminVerify");
        if (adminVerify == null || !"success".equals(adminVerify)){
            request.setAttribute("errorMsg","请完成管理员验证后再进行管理员功能操作!");
            request.getRequestDispatcher("/index").forward(request,response);
        }else {
            flag = true;
        }
        return flag;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
