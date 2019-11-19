import com.jason.message.ZxHttpUtil;
import com.jason.utils.MessageUtil;

import java.util.HashMap;

/**
 * @ClassName Test
 * @Description TODO
 * @Author GCJ
 * @Date 2019/11/19 9:24
 */
public class Test {
    public static void main(String[] args) {
        String Account = "15210594365";
// 您的短信账号密码
        String Password = "Ljt521125";
// 是否需要状态报告
        String NeedStatus = "true";
        String message = "用户您好，您正在注册移花接木账号,请勿将验证码告诉他人,您的验证码是:"+"5201314"+"。";//短信内容
        String mobile = "13051923045";//要发送的手机号,多个手机号用,隔开
        String ts =  MessageUtil.getNowDateStr();//时间戳


        Password = MessageUtil.getMD5(Account + Password + ts);// Md5签名(账号+密码+时间戳)
        HashMap params = new HashMap();
        //请求参数
        params.put("account",Account);
        params.put("pswd",Password);
        params.put("mobile",mobile);
        params.put("msg",message);
        params.put("ts",ts);
        params.put("needstatus",NeedStatus);
        //String rep = ZxHttpUtil.sendPostMessage("http://139.196.108.241:8080/Api/HttpSendSMYzm.ashx", params, "UTF-8");
        //System.out.println(rep);
    }
}
