package controller;

import aoplog.AopLog;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import rabbitmq.Producer;
import redis.JedisService;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/other")
public class UtilController {
    @Autowired
    private JedisService jedisService;
    @Autowired
    private Producer producer;
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

    /**
     * 图片裁剪的上传
     * @param image 图片的dataurl数据
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/uploadImage")
    public ModelAndView uploadImage(String image, HttpServletRequest request) throws IOException {
        ModelAndView md = new ModelAndView();
        if (image != null) {
            //截取图片数据，去除头部信息
            image = image.substring(image.indexOf(",")+1);
            //路径
            String path = "D:\\uploadForWeb\\";
            //对文件信息进行解码
            BASE64Decoder decoder = new BASE64Decoder();
            byte b[] = decoder.decodeBuffer(image);
           /*for (int i =0 ; i < b.length; i++){
                if (b[i] <0){
                    b[i] += 256;
                }
            }*/
            //写入文件
            String fileName = UUID.randomUUID().toString();
            File file = new File(path+fileName+".jpg");
            FileOutputStream out = new FileOutputStream(file);
            out.write(b);
            out.flush();
            out.close();
        }
        md.setViewName("redirect:/user/login");
        return md;
    }

    @RequestMapping(value = "/testrabbitmq")
    @ResponseBody
    public String sendMessage(){
        for (int i= 0; i< 10;i ++){
            producer.sendMessage(i);
        }
        return "ok";
    }
}
