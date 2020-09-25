package com.jason.article.service.impl;

import com.jason.article.domain.MyFollowEntity;
import com.jason.article.dto.MyFollowDto;
import com.jason.article.mapper.MyFollowMapper;
import com.jason.article.service.IMyFollowService;
import com.jason.domain.ResultVo;
import com.jason.utils.UUidUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName MyFollowServiceImpl
 * @Description TODO
 * @Author GCJ
 * @Date 2020/9/4 14:46
 */
@Service
public class MyFollowServiceImpl implements IMyFollowService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyFollowServiceImpl.class);

    @Autowired
    private MyFollowMapper myFollowMapper;

    /**
     * 保存我的关注
     * @param dto
     * @return
     */
    @Override
    public ResultVo saveMyFollowInfo(MyFollowDto dto) {
        ResultVo resultVo = new ResultVo();
        if(StringUtils.isBlank(dto.getUserId())){
            resultVo.setMessage("未获取到用户");
            resultVo.setOk(false);
            return resultVo;
        }
        dto.setId(UUidUtils.getUUid());
        dto.setCreateTime(new Date());
        try {
            myFollowMapper.saveMyFollowInfo(dto);
        } catch (Exception e) {
            LOGGER.error("添加失败:"+e.getMessage());
            resultVo.setMessage("系统异常添加失败");
            resultVo.setOk(false);
            return resultVo;
        }
        resultVo.setMessage("添加成功");
        resultVo.setOk(true);
        return resultVo;
    }

    @Override
    public ResultVo queryMyFollowInfoByUserId(String userId) {
        ResultVo resultVo = new ResultVo();
        if(StringUtils.isBlank(userId)){
            resultVo.setMessage("未获取到用户");
            resultVo.setOk(false);
            return resultVo;
        }
        List<MyFollowEntity> list = null;
        try {
            list = myFollowMapper.queryMyFollowInfoByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
            resultVo.setOk(false);
            resultVo.setData("系统异常，未获取到关注列表");
            return resultVo;
        }
        resultVo.setOk(true);
        resultVo.setData(list);
        return resultVo;
    }
}
