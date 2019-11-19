package com.jason.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jason.consts.JasonConst;
import com.jason.domain.ResultVo;
import com.jason.message.ZxHttpUtil;
import com.jason.user.domain.UserInfoDto;
import com.jason.user.mapper.UserMapper;
import com.jason.user.service.UserService;
import com.jason.utils.JwtUtil;
import com.jason.utils.Md5Util;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author GCJ
 * @Date 2019/11/11 9:40
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER  = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 注册
     * @param userInfoDto
     * @return
     */
    @Override
    public ResultVo registerUser(UserInfoDto userInfoDto) {
        ResultVo result = new ResultVo();
        String userName = userInfoDto.getUserName();
        String passWord = userInfoDto.getPassWord();
        if(StringUtils.isBlank(userName) && StringUtils.isBlank(passWord)){
            result.setMessage("用户名或密码为空");
            return result;
        }

        /**
         * 验证用户名密码长度  后期优化
         */
        if(!checkUserAndPwdLength(userInfoDto)){
            result.setMessage("用户名或密码长度不够");
            return result;
        }

        if(StringUtils.isNotBlank(userInfoDto.getPhoneNumber())){
            int count = userMapper.checkUserPhone(userInfoDto.getPhoneNumber());
            if(count>0){
                result.setMessage("不好意思,您输入的手机号已存在");
                return result;
            }
        }

        try {
            String user = userMapper.checkUserName(userName);
            if(userInfoDto.getUserName().equals(user)){
                result.setMessage("用户名已存在");
                LOGGER.info("用户名已存在:"+user);
                return result;
            }

            //注册信息
            userInfoDto.setId(UUID.randomUUID().toString());
            userInfoDto.setCreateTime(new Date());
            userInfoDto.setModifyTime(userInfoDto.getCreateTime());
            //MD5加密
            userInfoDto.setPassWord(Md5Util.md5password(passWord));
            userInfoDto.setActive(JasonConst.STR_Y);
            userMapper.registerUser(userInfoDto);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("用户注册失败:"+e.getMessage());
            result.setCode(500);
            result.setMessage("用户注册失败");
            result.setOk(false);
            return result;
        }
        result.setCode(200);
        result.setOk(true);
        result.setMessage("用户注册成功");
        return result;
    }

    /**
     * 验证 用户名子是否存在
     * @param map
     */
    @Override
    public ResultVo checkUserName(Map<String, String> map) {
        ResultVo result = new ResultVo();
        String userName = map.get("userName");
        if(StringUtils.isBlank(userName)){
            LOGGER.info("用户输入的信息为空或空格");
        }
        try {
            String user = userMapper.checkUserName(userName);
            result.setCode(200);
            result.setOk(true);
            result.setData(user);
        } catch (Exception e) {
            LOGGER.info("用户输入的信息为空或空格");
            e.printStackTrace();
            result.setCode(500);
            result.setMessage("用户注册失败");
            result.setOk(false);
            return result;
        }
        return result;
    }

    /**
     * 登录
     * @param userInfo
     * @return
     */
    @Override
    public ResultVo loginUser(UserInfoDto userInfo) {
        ResultVo result = new ResultVo();
        String userName = userInfo.getUserName();
        String passWord = userInfo.getPassWord();
        if(StringUtils.isBlank(userName) && StringUtils.isBlank(passWord)){
            result.setMessage("用户名或密码为空");
            return result;
        }

        /**
         * 验证用户名密码长度  后期优化
         */
        if(!checkUserAndPwdLength(userInfo)){
            result.setMessage("用户名或密码长度不够");
            return result;
        }
        userInfo.setPassWord(Md5Util.md5password(passWord));
        UserInfoDto userResult = userMapper.checkUserPwd(userInfo);
        if(null != userResult){
            if(StringUtils.isNotBlank(userResult.getPassWord())){
                Map<String, Object> map = new HashMap<>();
                map.put("userName",userResult.getUserName());
                map.put("passWord",userResult.getPassWord());
                //生成Token
                String token = JwtUtil.createToken(map);
                LOGGER.info("token:"+token);
                //1.大key  2.小key 3.Token 编码
                //redisTemplate.opsForHash().put("token",userResult.getUserName(),token);
                result.setMessage("验证成功");
                result.setData(token);
                result.setCode(200);
                result.setOk(true);
                return result;
            }
        }
            result.setMessage("验证失败,密码错误");
            result.setOk(false);
            return result;
    }

    @Override
    public ResultVo phoneLoginUser(Map<String, Object> map) {
        ResultVo result = new ResultVo();
        String phone = (String) map.get("phoneNumber");
        String code = (String) map.get("code");
        if(StringUtils.isBlank(phone)){
            LOGGER.info("用户输入的手机号为空或空格");
            result.setMessage("用户输入的手机号为空或空格");
        }
        if(StringUtils.isBlank(code)){
            LOGGER.info("用户输入的验证码为空或空格");
            result.setMessage("用户输入的验证码为空或空格");
        }
        try {
            String phoneCode = (String) redisTemplate.opsForHash().get("phone", phone);
            if(code.equals(phoneCode)){
                result.setCode(200);
                result.setOk(true);
                result.setMessage("验证成功");
            }else{
                result.setCode(201);
                result.setOk(false);
                result.setMessage("验证失败");
            }
        } catch (Exception e) {
            LOGGER.info("获取Redis的信息失败:"+e.getMessage());
            e.printStackTrace();
            result.setCode(500);
            result.setMessage("用户登录失败");
            result.setOk(false);
            return result;
        }
        return result;
    }

    @Override
    public ResultVo sendCode(Map<String, String> map) {
        ResultVo result = new ResultVo();
        String phone = (String) map.get("phoneNumber");
        if(StringUtils.isBlank(phone)){
            result.setMessage("用户输入的手机号为空或空格");
        }
        int count = userMapper.checkUserPhone(phone);
        if(count<=0){
            result.setMessage("不好意思!您输入的手机号不存在");
            return result;
        }
        int code = (int) ((Math.random() * 9 + 1) * 100000);
        try {
            String rep = ZxHttpUtil.sendPostMessage(phone,code);
            redisTemplate.opsForHash().put("phone", phone,code+"");
            JSONObject jsonObject = JSONObject.parseObject(rep);
            if(jsonObject != null){
                String str = (String) jsonObject.get("result_msg");
                if("提交成功".equals(str)){
                    result.setOk(true);
                    result.setMessage("验证码发送成功");
                }else{
                    result.setMessage("验证码发送失败");
                }
            }else {
                result.setMessage("验证码发送失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("验证码发送失败,系统异常");
        }
        return result;
    }

    /**
     * 验证字符串密码长度
     * @param userInfoDto
     * @return
     */
    public boolean checkUserAndPwdLength(UserInfoDto userInfoDto){
        int pwdlength = userInfoDto.getPassWord().length();
        int userlength = userInfoDto.getUserName().length();
        if(pwdlength >= 8 && pwdlength <=16 && userlength>=6 && userlength<=16){
            return true;
        }
        return false;
    }

}
