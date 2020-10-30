package com.jason.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jason.consts.JasonConstant;
import com.jason.consts.RedisConstant;
import com.jason.domain.ResultVo;
import com.jason.message.ZxHttpUtil;
import com.jason.service.RedisCacheService;
import com.jason.user.domain.UserInfoDto;
import com.jason.user.domain.UserInfoEntity;
import com.jason.user.mapper.UserMapper;
import com.jason.user.service.UserService;
import com.jason.utils.JwtUtil;
import com.jason.utils.Md5Util;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.concurrent.TimeUnit;

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
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisCacheService redisCacheService;

    private static final String USER = "USER_";

    /**
     * 注册
     * @param userInfoDto
     * @return
     */
    @Override
    public ResultVo registerUser(UserInfoDto userInfoDto) {
        ResultVo result = new ResultVo();
        String userName = userInfoDto.getUsername();
        String passWord = userInfoDto.getPassword();
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
            if(userInfoDto.getUsername().equals(user)){
                result.setMessage("用户名已存在");
                LOGGER.info("用户名已存在:"+user);
                return result;
            }
            long account = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
            String key = USER + account;
            String value = account+"";
            /**
             * 当账号重复的时候
             */
            if(stringRedisTemplate.hasKey(key)){
                LOGGER.info("并发数据:"+key+"重新生成账号");
                account = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
                int var = userMapper.checkUserAccount(account);
                account = var == 0 ? account : getRandomNickname(10);
                LOGGER.info("生成后的账号:"+account);
            }
            stringRedisTemplate.opsForValue().set(key,value,1,TimeUnit.SECONDS);
            //注册信息
            userInfoDto.setId(UUID.randomUUID().toString());
            userInfoDto.setAccount(account);
            userInfoDto.setCreateTime(new Date());
            userInfoDto.setModifyTime(userInfoDto.getCreateTime());
            //MD5加密
            userInfoDto.setPassword(Md5Util.md5password(passWord));
            userInfoDto.setActive(JasonConstant.STR_Y);
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
        String username = map.get("username");
        if(StringUtils.isBlank(username)){
            LOGGER.info("用户输入的信息为空或空格");
        }
        try {
            String user = userMapper.checkUserName(username);
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
        LOGGER.info("user is login:{}",JSON.toJSONString(userInfo.getUsername()));
        ResultVo result = new ResultVo();
        String username = userInfo.getUsername();
        String password = userInfo.getPassword();
        if(StringUtils.isBlank(username) && StringUtils.isBlank(password)){
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
        userInfo.setPassword(Md5Util.md5password(password));
        UserInfoDto userResult = userMapper.checkUserPwd(userInfo);
        if(null != userResult){
            if(StringUtils.isNotBlank(userResult.getPassword())){
                Map<String, Object> map = new HashMap<>();
                map.put("username",userResult.getUsername());
                map.put("password",userResult.getPassword());
                String token ="";
                //1.大key  2.小key 3.Token 编码
                if(!stringRedisTemplate.hasKey(RedisConstant.LOGIN+username)){
                    //生成Token
                    token = JwtUtil.createToken(map);
                    stringRedisTemplate.opsForValue().set(RedisConstant.LOGIN+username,token);
                    stringRedisTemplate.expire(RedisConstant.LOGIN+username,1, TimeUnit.DAYS);
                }else{
                    token = stringRedisTemplate.opsForValue().get(RedisConstant.LOGIN+username);
                }
                LOGGER.info("token:"+token);
                UserInfoEntity user = new UserInfoEntity();
                user.setToken(token);
                user.setAccount(userResult.getAccount());
                user.setUsername(userResult.getUsername());
                result.setMessage("验证成功");
                result.setData(user);
                result.setCode(200);
                result.setOk(true);
                return result;
            }
        }
        result.setMessage("密码输入错误");
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
            String phoneCode = (String) stringRedisTemplate.opsForHash().get("phone", phone);
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
            stringRedisTemplate.opsForHash().put("phone", phone,code+"");
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
     * 修改用户密码
     * @param map
     * @return
     */
    @Override
    public ResultVo updatePwd(Map<String, String> map) {
        String username = map.get("username");//用户名
        String oldPwd = map.get("oldPwd");//老密码
        String newPwd = map.get("newPwd");//新密码
        String rePwd = map.get("rePwd");//确认密码
        if(StringUtils.isBlank(username) && StringUtils.isBlank(oldPwd) &&
                StringUtils.isBlank(newPwd) && StringUtils.isBlank(rePwd)){
            LOGGER.info("用户名或密码为null");
            return new ResultVo(false,500,"用户名或密码为null");
        }

        if(!newPwd.equals(rePwd)){
            LOGGER.info("新密码和旧密码不一样...");
            return new ResultVo(false,500,"新密码和确认密码不一致");
        }

        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setPassword(Md5Util.md5password(oldPwd));
        userInfoDto.setUsername(username);
        try {
            UserInfoDto userResult = userMapper.checkUserPwd(userInfoDto);
            if(null != userResult){
                userInfoDto.setPassword(Md5Util.md5password(newPwd));
                userInfoDto.setId(userResult.getId());
                userMapper.updatePwd(userInfoDto);
                return new ResultVo(true,200,"用户:"+username+"密码修改成功");
            }
            return new ResultVo(false,500,"旧密码不对");
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("密码修改失败:"+e.getMessage());
            return new ResultVo(false,500,"密码修改失败");
        }
    }

    @Override
    public ResultVo userLogout(Map<String, String> map) {
        if(null == map || StringUtils.isBlank(map.get("username"))){
            return new ResultVo(false,500,"未获取到登陆信息");
        }
        String username = map.get("username");
        String token = map.get("token");
        if(token.equals(redisCacheService.get(RedisConstant.LOGIN + username))){
            redisCacheService.del(RedisConstant.LOGIN + username);
            LOGGER.info("user :"+username+" is logout");
            return new ResultVo(true,200,"退出登录");
        }
        return new ResultVo(false,500,"未获取到登陆信息");
    }

    /**
     * 验证字符串密码长度
     * @param userInfoDto
     * @return
     */
    public boolean checkUserAndPwdLength(UserInfoDto userInfoDto){
        int pwdlength = userInfoDto.getPassword().length();
        int userlength = userInfoDto.getUsername().length();
        if(pwdlength >= 8 && pwdlength <=16 && userlength>=6 && userlength<=16){
            return true;
        }
        return false;
    }

    /**
     * 生成重复数字
     * @param length
     * @return
     */
    public static long getRandomNickname(int length) {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            val += String.valueOf(random.nextInt(10));
        }
        return Long.valueOf(val);
    }


}
