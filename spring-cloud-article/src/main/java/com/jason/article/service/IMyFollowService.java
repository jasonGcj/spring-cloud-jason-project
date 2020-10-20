package com.jason.article.service;

import com.jason.article.dto.MyFollowDto;
import com.jason.domain.ResultVo;

/**
 * @ClassName IMyFollowService
 * @Description TODO
 * @Author GCJ
 * @Date 2020/9/4 14:44
 */
public interface IMyFollowService {
    ResultVo queryMyFollowInfoByUserId(String userId);
}
