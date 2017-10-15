package converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lh
 */
public class StringToDateConverter implements Converter<String,Date>{
    private String dataPattern;
    public void setDataPattern(String dataPattern){
        this.dataPattern = dataPattern;
    }
    @Override
    public Date convert(String s) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(dataPattern);
        try {
            return dateFormat.parse(s);
        } catch (ParseException e) {
            //e.printStackTrace();
            return null;
        }
    }
}
