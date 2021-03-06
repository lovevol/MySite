package service;

import dao.EbookDAO;
import model.Ebook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lh
 * on 2017/9/11.
 * @author lh
 */
@Service
public class EbookService {
    @Autowired
    private EbookDAO ebookDAO;

    public List<Ebook> getEbookForIndex(String keyWord){
        return ebookDAO.getEbookForIndex(keyWord);
    }

    public int addEbook(Ebook ebook){
        return ebookDAO.addEbook(ebook);
    }

    public List<Ebook> getEbook(){
        return ebookDAO.selectAllEbook();
    }

    public int deleteEbookById(int id){
        return ebookDAO.deleteEbookById(id);
    }
}
