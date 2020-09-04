package com.jason.article.mapper;

import com.jason.article.domain.MyFollowEntity;
import com.jason.article.dto.MyFollowDto;
import com.jason.domain.ResultVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName MyFollowMapper
 * @Description TODO
 * @Author GCJ
 * @Date 2020/9/4 14:50
 */
@Mapper
public interface MyFollowMapper {
    void saveMyFollowInfo(MyFollowDto dto);

    List<MyFollowEntity> queryMyFollowInfoByUserId(String userId);
}
