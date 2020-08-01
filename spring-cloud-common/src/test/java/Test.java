import com.jason.message.ZxHttpUtil;
import com.jason.utils.MessageUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * @ClassName Test
 * @Description TODO
 * @Author GCJ
 * @Date 2019/11/19 9:24
 */
public class Test {
    public static void main(String[] args) {
        Date date = new Date(20200603141152L);
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        System.out.println(format);
    }}
