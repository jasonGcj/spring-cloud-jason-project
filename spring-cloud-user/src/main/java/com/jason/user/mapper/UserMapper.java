package com.jason.user.mapper;

import com.jason.user.domain.UserInfoDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName UserMapper
 * @Description TODO
 * @Author GCJ
 * @Date 2019/11/11 9:43
 */
@Mapper
public interface UserMapper {
    void registerUser(UserInfoDto userInfoDto);

    String checkUserName(@Param("userName") String userName);

    UserInfoDto checkUserPwd(UserInfoDto userInfo);
}
