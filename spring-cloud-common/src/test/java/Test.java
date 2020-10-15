import com.alibaba.fastjson.JSON;
import com.jason.message.ZxHttpUtil;
import com.jason.utils.MessageUtil;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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

        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
    // 获取对应的平方数
        List<Integer> squaresList = numbers.stream().map( i -> i+i ).distinct().collect(Collectors.toList());
        System.out.println(JSON.toJSONString(squaresList));
    }}
