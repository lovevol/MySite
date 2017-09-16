package service;

import dao.WebDAO;
import model.Web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lh
 * on 2017/9/11.
 */
@Service
public class WebService {
    @Autowired
    private WebDAO webDAO;

    public List<Web> getWebForIndex(){
        return webDAO.getWebForIndex();
    }

    public int addWeb(Web web){
        return webDAO.addWeb(web);
    }
}
