package util;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lh
 * on 2018/3/1.
 */
public class MyExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView md = new ModelAndView();
        if (ex != null){
            md.addObject("errorMsg",ex.getMessage());
        }
        md.setViewName("/page/other/error.jsp");
        return md;
    }
}
