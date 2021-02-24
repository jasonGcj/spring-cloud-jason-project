package com.jason.user.service;

import com.jason.domain.ResultVo;
import com.jason.user.domain.TestQueryDto;

/**
 * @ClassName TestService
 * @Description TODO
 * @Author GCJ
 * @Date 2021/2/24 20:51
 */
public interface TestService {
    ResultVo testQueryData(TestQueryDto dto);

    ResultVo addData(TestQueryDto dto);
}
