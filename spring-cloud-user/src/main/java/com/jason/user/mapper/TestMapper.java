package com.jason.user.mapper;

import com.jason.user.domain.TestQueryDto;
import com.jason.user.domain.TestQueryEnity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName TestMapper
 * @Description TODO
 * @Author GCJ
 * @Date 2021/2/24 20:54
 */
@Mapper
public interface TestMapper {
    List<TestQueryEnity> testQueryData(TestQueryDto dto);

    void addData(TestQueryDto dto);
}
