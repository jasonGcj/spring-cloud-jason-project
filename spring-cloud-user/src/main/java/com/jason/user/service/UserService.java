package com.jason.user.service;

import com.jason.domain.ResultVo;
import com.jason.user.domain.UserInfoDto;

import java.util.Map;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author GCJ
 * @Date 2019/11/11 9:40
 */
public interface UserService {
    /**
     * 用户注册
     * @param userInfoDto
     * @return
     */
    ResultVo registerUser(UserInfoDto userInfoDto);
    /**
     * 验证用户名的唯一性
     * @param map
     * @return
     */
    ResultVo checkUserName(Map<String, String> map);

    /**
     * 登录
     * @param userInfo
     * @return
     */
    ResultVo loginUser(UserInfoDto userInfo);

    ResultVo phoneLoginUser(Map<String, Object> map);

    ResultVo sendCode(Map<String, String> map);

    ResultVo updatePwd(Map<String, String> map);

    ResultVo userLogout(Map<String, String> map);
}
