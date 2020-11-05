package com.jason.utils;

import com.jason.domain.ResultVo;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * @ClassName sendEmails
 * @Description TODO
 * @Author GCJ
 * @Date 2020/11/2 10:39
 */
public class EmailUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailUtils.class);

    public static final String EMAIL_126 = "smtp.126.com";
    public static final String EMAIL_163 = "163.smtp.com";
    public static final String EMAIL_QQ = "smtp.qq.com";

    public static final String EMAIL="jason_gu12@126.com";
    public static final String EMAIL_USER="jason";
    public static final String EMAIL_PASSWORD="ECXSKJJESYSLUSVS";

    public static ResultVo sendEmail(String emailAddress, String code){
        if(StringUtils.isBlank(emailAddress)){
            return new ResultVo(false,500,"邮箱地址为空");
        }
        if(StringUtils.isBlank(code)){
            return new ResultVo(false,500,"验证码为空");
        }
        try {
            //不用更改
            HtmlEmail email = new HtmlEmail();
            //需要修改，126邮箱为smtp.126.com,163邮箱为163.smtp.com，QQ为smtp.qq.com
            email.setHostName(EMAIL_126);
            email.setCharset("UTF-8");
            // 收件地址
            email.addTo(emailAddress);
            //此处填邮箱地址和用户名,用户名可以任意填写
            email.setFrom(EMAIL, EMAIL_USER);
            //此处填写邮箱地址和客户端授权码
            email.setAuthentication(EMAIL, EMAIL_PASSWORD);
            //此处填写邮件名，邮件名可任意填写
            email.setSubject("注册验证码");
            //此处填写邮件内容
            email.setMsg("尊敬的用户您好,您本次注册的验证码是:" + code);
            //            email.setSSLOnConnect(false);
            //启用ssl加密
            email.setSSLOnConnect(true);
            //使用465端口(不设置也可，ssl默认为465)
            email.setSslSmtpPort("465");
            email.send();
            LOGGER.info("send email is success :"+emailAddress);
            return new ResultVo(true,200,"邮箱发送成功");
        } catch (EmailException e) {
            e.printStackTrace();
            LOGGER.info("send email is fail :"+e.getMessage());
        }
        return new ResultVo(false,500,"邮箱发送失败");

    }
}
