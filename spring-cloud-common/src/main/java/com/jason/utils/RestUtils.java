package com.jason.utils;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName RestUtils
 * @Description TODO
 * @Author GCJ
 * @Date 2020/10/20 9:16
 */
public class RestUtils {
    private static final Logger log = LoggerFactory.getLogger(RestUtils.class);

    @LoadBalanced
    private static RestTemplate restTemplate = new RestTemplate();

    // 默认时间五秒，不重试。
    private final static int CONNEC_TIMEOUT = 5000;
    private final static int READ_TIMEOUT   = 5000;
    private final static int RETRY_COUNT    = 1;


    /**
     * https 请求 GET
     * @param url           地址
     * @param connecTimeout 连接时间  毫秒
     * @param readTimeout   读取时间  毫秒
     * @param retryCount    重试机制
     * @return String 类型
     */
    public static String getHttp(String url, int connecTimeout, int readTimeout, int retryCount) {
        RestTemplate restTemplate = simpeClient(url, connecTimeout, readTimeout);
        String result = null; // 返回值类型;
        for (int i = 1; i <= retryCount; i++) {
            try {
                result = restTemplate.getForObject(url, String.class);
                return result;
            } catch (RestClientException e) {
                log.error("-----------开始-----------重试count: " + i);
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * https 请求 GET
     * @param url           地址
     * @return String 类型
     */
    public static String getHttp(String url) {
        RestTemplate restTemplate = simpeClient(url, CONNEC_TIMEOUT, READ_TIMEOUT);
        String result = null; // 返回值类型;
        for (int i = 1; i <= RETRY_COUNT; i++) {
            try {
                result = restTemplate.getForObject(url, String.class);
                return result;
            } catch (RestClientException e) {
                log.error("-----------开始-----------重试count: " + i);
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * https 请求 GET
     * @param url           地址
     * @return String 类型
     */
    public static String getHttp(String url,Map map) {
        StringBuilder sb = new StringBuilder();
        Set<String> sets = map.keySet();
        Iterator<String> iterator = sets.iterator();
        sb.append(url);
        sb.append("?");
        while (iterator.hasNext()){
            String key = iterator.next().toString();
            String value = map.get(key).toString();
            sb.append(key);
            sb.append("=");
            sb.append(value);
            if(iterator.hasNext()){
                sb.append("&");
            }
        }
        log.info("url is :"+sb.toString());
        RestTemplate restTemplate = simpeClient(sb.toString(), CONNEC_TIMEOUT, READ_TIMEOUT);
        String result = null; // 返回值类型;
        for (int i = 1; i <= RETRY_COUNT; i++) {
            try {
                result = restTemplate.getForObject(sb.toString(), String.class);
                return result;
            } catch (RestClientException e) {
                log.error("-----------开始-----------重试count: " + i);
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * http 请求 post
     * @param url           地址
     * @param params        参数
     * @param headersMap    header
     * @param connecTimeout 连接时间
     * @param readTimeout   读取时间`
     * @param retryCount    重试机制
     * @return String 类型
     */
    public static String postHttp(String url, JSONObject params, Map headersMap, int connecTimeout, int readTimeout, int retryCount) {
        RestTemplate restTemplate =  simpeClient(url, connecTimeout, readTimeout);
        // 设置·header信息
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setAll(headersMap);
        HttpEntity<JSONObject> requestEntity = new HttpEntity<JSONObject>(params, requestHeaders); // josn utf-8 格式
        String result = null; // 返回值类型;
        for (int i = 1; i <= retryCount; i++) {
            try {
                result = restTemplate.postForObject(url, requestEntity, String.class);
                return result;
            } catch (RestClientException e) {
                log.error("-----------开始-----------重试count: " + i);
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * http 请求 post
     * @param url           地址
     * @param params        参数
     * @param headersMap    header
     * @return String 类型
     */
    public static String postHttp(String url, JSONObject params, Map headersMap) {
        RestTemplate restTemplate = simpeClient(url, CONNEC_TIMEOUT, READ_TIMEOUT);
        // 设置·header信息
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setAll(headersMap);
        HttpEntity<JSONObject> requestEntity = new HttpEntity<JSONObject>(params, requestHeaders); // josn utf-8 格式
        String result = null; // 返回值类型;
        for (int i = 1; i <= RETRY_COUNT; i++) {
            try {
                result = restTemplate.postForObject(url, requestEntity, String.class);
                return result;
            } catch (RestClientException e) {
                log.error("-----------开始-----------重试count: " + i);
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * 加密参数类型请求  application/x-www-form-urlencoded
     * MultiValueMap<String, Object>
     * 采用 HttpEntity<MultiValueMap<String, Object>> 构造
     * http 请求 post
     *
     * @param url           地址
     * @param postParameters 参数
     * @param headersMap    header
     * @param connecTimeout 连接时间
     * @param readTimeout   读取时间
     * @param retryCount    重试机制
     * @return String 类型
     */
    public static String postHttpEncryption(String url, MultiValueMap<String, Object> postParameters, Map headersMap, int connecTimeout, int readTimeout, int retryCount) {
        RestTemplate restTemplate = simpeClient(url, connecTimeout, readTimeout);
        // 设置·header信息
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setAll(headersMap);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(postParameters, requestHeaders);
        String result = null; // 返回值类型;
        for (int i = 1; i <= retryCount; i++) {
            try {
                result = restTemplate.postForObject(url, requestEntity, String.class);
                return result;
            } catch (RestClientException e) {
                log.error("-----------开始-----------重试count: " + i);
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 加密参数类型请求  application/x-www-form-urlencoded
     * MultiValueMap<String, Object>
     * 采用 HttpEntity<MultiValueMap<String, Object>> 构造
     * http 请求 post
     * @param url           地址
     * @param postParameters 参数
     * @param headersMap    header
     * @return String 类型
     */
    public static String postHttpEncryption(String url, MultiValueMap<String, Object> postParameters, Map headersMap) {
        RestTemplate restTemplate = simpeClient(url, CONNEC_TIMEOUT, READ_TIMEOUT);
        // 设置·header信息
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setAll(headersMap);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(postParameters, requestHeaders);
        String result = null; // 返回值类型;
        for (int i = 1; i <= RETRY_COUNT; i++) {
            try {
                result = restTemplate.postForObject(url, requestEntity, String.class);
                return result;
            } catch (RestClientException e) {
                log.error("-----------开始-----------重试count: " + i);
                e.printStackTrace();
            }
        }
        return null;
    }





    private static RestTemplate simpeClient(String url, int connecTimeout, int readTimeout) {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(connecTimeout);
        requestFactory.setReadTimeout(readTimeout);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8)); // 设置编码集
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler()); //error处理
        if (url.contains("https")){
            log.info("https is error please call author");
            //restTemplate.setRequestFactory(new HttpsClientRequestFactory()); // 绕过https
        }
        return restTemplate;
    }


    /**
     * @ClassName: DefaultResponseErrorHandler
     * @Description: TODO
     * @author:
     * @date: 2
     */
    private static class DefaultResponseErrorHandler implements ResponseErrorHandler {

        /**
         * 对response进行判断，如果是异常情况，返回true
         */
        @Override
        public boolean hasError(ClientHttpResponse response) throws IOException {
            return response.getStatusCode().value() != HttpServletResponse.SC_OK;
        }

        /**
         * 异常情况时的处理方法
         */
        @Override
        public void handleError(ClientHttpResponse response) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(response.getBody()));
            StringBuilder sb = new StringBuilder();
            String str = null;
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            try {
                throw new Exception(sb.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
