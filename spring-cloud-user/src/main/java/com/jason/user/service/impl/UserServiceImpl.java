package com.jason.user.service.impl;

import com.jason.consts.JasonConst;
import com.jason.domain.ResultVo;
import com.jason.user.domain.UserInfoDto;
import com.jason.user.mapper.UserMapper;
import com.jason.user.service.UserService;
import com.jason.utils.JwtUtil;
import com.jason.utils.Md5Util;
import org.apache.commons.lang.RandomStringUtils;
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
            result.setCode(200);
            result.setOk(true);
            return result;
        }else{
            result.setMessage("验证失败,密码错误");
            result.setOk(false);
            return result;
        }

    }

    /**
     * 验证字符串密码长度
     * @param userInfoDto
     * @return
     */
    public boolean checkUserAndPwdLength(UserInfoDto userInfoDto){
        int pwdlength = userInfoDto.getPassWord().length();
        int userlength = userInfoDto.getUserName().length();
        if(pwdlength > 6 && pwdlength <16 && userlength>8 && userlength<16){
            return true;
        }
        return false;
    }

}
