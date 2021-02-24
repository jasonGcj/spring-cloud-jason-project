package com.jason.user.controller;

import com.jason.domain.ResultVo;
import com.jason.user.domain.EmailDto;
import com.jason.user.domain.UserInfoDto;
import com.jason.user.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author GCJ
 * @Date 2019/11/11 9:30
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService ;

    /**
     * 注册用户
     * @param userInfo
     * @return
     */
    @RequestMapping("/register")
    public ResultVo registerUser(@RequestBody UserInfoDto userInfo){
        return userService.registerUser(userInfo);
    }
    /**
     * 登录
     * @param userInfo
     * @return
     */
    @RequestMapping("/login")
    public ResultVo loginUser(@RequestBody UserInfoDto userInfo) throws Exception {
        return userService.loginUser(userInfo);
    }
    /**
     * 退出登录
     * @param map
     * @return
     */
    @RequestMapping("/loginout")
    public ResultVo userLogout(@RequestBody Map<String,String> map) throws Exception {
        return userService.userLogout(map);
    }

    /**
     * 验证用户名的唯一性
     * @param map
     * @return
     */
    @RequestMapping("/checkUserName")
    public ResultVo checkUserName(@RequestBody Map<String,String> map){
        return userService.checkUserName(map);
    }

    /**
     * 手机号登录
     * @param map
     * @return
     */
    @RequestMapping("/phoneLogin")
    public ResultVo phoneLoginUser(@RequestBody Map<String,Object> map){
        return userService.phoneLoginUser(map);
    }

    /**
     * 发送验证码
     * @param map
     * @return
     */
    @RequestMapping("/sendCode")
    public ResultVo sendCode(@RequestBody Map<String,String> map){
        return userService.sendCode(map);
    }

    /**
     * 修改密码
     * @param map
     * @return
     */
    @RequestMapping("/updatePwd")
    public ResultVo updatePwd(@RequestBody Map<String,String> map){
        return userService.updatePwd(map);
    }

    @RequestMapping("/sendEmail")
    public ResultVo sendEmail(@RequestBody EmailDto dto){
        return userService.sendEmail(dto);
    }

}
