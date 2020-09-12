package com.jason.message;

import com.jason.consts.MessageConstant;
import com.jason.utils.MessageUtil;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ZxHttpUtil
 * @Description TODO
 * @Author GCJ
 * @Date 2019/11/19 9:17
 */
public class ZxHttpUtil {

    // 表示服务器端的url

    private ZxHttpUtil() {
        // TODO Auto-generated constructor stub
    }


    /*
     * params 填写的URL的参数 encode 字节编码
     */
    public static String sendPostMessage( String phone ,int code) {
        String message = "用户你好,您在正在注册个人博客:"+code+",请勿告诉ta人";
        String ts = MessageUtil.getNowDateStr();
        String passWord = MessageUtil.getMD5(MessageConstant.ACCOUNT + MessageConstant.PASSWORD + ts);// Md5签名(账号+密码+时间戳)
        Map<String, String> params = new HashMap<>();
        params.put("account", MessageConstant.ACCOUNT);
        params.put("pswd",passWord);
        params.put("mobile",phone);
        params.put("msg",message);
        params.put("ts", ts);
        params.put("needstatus", MessageConstant.NEED_STATUS);
        URL url = null;
        try {
            url = new URL(MessageConstant.URL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        System.out.println(params.get("pswd"));
        StringBuffer stringBuffer = new StringBuffer();

        if (params != null && !params.isEmpty()) {
            System.out.println("ddd");
            for (Map.Entry<String, String> entry : params.entrySet()) {
                try {
                    stringBuffer
                            .append(entry.getKey())
                            .append("=")
                            .append(URLEncoder.encode(entry.getValue(), "utf-8"))
                            .append("&");

                } catch (UnsupportedEncodingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            // 删掉最后一个 & 字符
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            System.out.println("-->>" + stringBuffer.toString());

            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) url
                        .openConnection();
                httpURLConnection.setConnectTimeout(3000);
                httpURLConnection.setDoInput(true);// 从服务器获取数据
                httpURLConnection.setDoOutput(true);// 向服务器写入数据

                // 获得上传信息的字节大小及长度
                byte[] mydata = stringBuffer.toString().getBytes();
                // 设置请求体的类型
                httpURLConnection.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");
                httpURLConnection.setRequestProperty("Content-Lenth",
                        String.valueOf(mydata.length));

                // 获得输出流，向服务器输出数据
                OutputStream outputStream = (OutputStream) httpURLConnection
                        .getOutputStream();
                outputStream.write(mydata);

                // 获得服务器响应的结果和状态码
                int responseCode = httpURLConnection.getResponseCode();
                System.out.println(responseCode);
                if (responseCode == 200) {

                    // 获得输入流，从服务器端获得数据
                    InputStream inputStream = (InputStream) httpURLConnection
                            .getInputStream();
                    return (changeInputStream(inputStream, "utf-8"));

                }

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("eee");
        return "";
    }
        /*
         * // 把从输入流InputStream按指定编码格式encode变成字符串String
         */
        public static String changeInputStream(InputStream inputStream,
                String encode) {

            // ByteArrayOutputStream 一般叫做内存流
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] data = new byte[1024];
            int len = 0;
            String result = "";
            if (inputStream != null) {

                try {
                    while ((len = inputStream.read(data)) != -1) {
                        byteArrayOutputStream.write(data, 0, len);

                    }
                    result = new String(byteArrayOutputStream.toByteArray(), encode);

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

            return result;
        }
}
