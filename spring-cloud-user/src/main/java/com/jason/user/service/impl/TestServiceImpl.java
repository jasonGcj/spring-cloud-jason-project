package com.jason.user.service.impl;

import com.jason.domain.ResultVo;
import com.jason.user.domain.TestQueryDto;
import com.jason.user.domain.TestQueryEnity;
import com.jason.user.mapper.TestMapper;
import com.jason.user.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @ClassName TestServiceImpl
 * @Description TODO
 * @Author GCJ
 * @Date 2021/2/24 20:51
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;

    @Override
    public ResultVo testQueryData(TestQueryDto dto) {
        List<TestQueryEnity> list = testMapper.testQueryData(dto);
        ResultVo result = new ResultVo();
        result.setData(list);
        result.setCode(200);
        result.setOk(true);
        result.setMessage("查询成功");
        return result;
    }

    @Override
    public ResultVo addData(TestQueryDto dto) {
        ResultVo result = new ResultVo();
        if(null == dto){
            return new ResultVo(false,101,"参数为空");
        }
        dto.setId(UUID.randomUUID().toString());
        testMapper.addData(dto);
        return new ResultVo(true,200,"添加成功");
    }

}
