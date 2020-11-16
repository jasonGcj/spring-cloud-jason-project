import com.alibaba.fastjson.JSON;
import com.jason.message.ZxHttpUtil;
import com.jason.utils.MessageUtil;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

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
    public static void main(String[] args){
        //不用更改
        try {
            HtmlEmail email = new HtmlEmail();
            //需要修改，126邮箱为smtp.126.com,163邮箱为163.smtp.com，QQ为smtp.qq.com
            email.setHostName("smtp.126.com");
            email.setCharset("UTF-8");
            // 收件地址
            email.addTo("850849967@qq.com");
            //此处填邮箱地址和用户名,用户名可以任意填写
            email.setFrom("jason_gu12@126.com", "jason");
            //此处填写邮箱地址和客户端授权码
            email.setAuthentication("jason_gu12@126.com", "ECXSKJJESYSLUSVS");
            //此处填写邮件名，邮件名可任意填写
            email.setSubject("注册验证码");
            //此处填写邮件内容
            email.setMsg("尊敬的用户您好,您本次注册的验证码是:" + "123456");
            //            email.setSSLOnConnect(false);
            //启用ssl加密
            email.setSSLOnConnect(false);
            //使用465端口(不设置也可，ssl默认为465)
            email.send();
            System.out.println("发送成功");
        } catch (EmailException e) {
            e.printStackTrace();
            System.out.println("发送异常:"+e.getMessage());
        }
    }
}
