package com.jason.article.controller;

import com.jason.article.dto.MyFollowDto;
import com.jason.article.service.IMyFollowService;
import com.jason.domain.ResultVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

/**
 * @ClassName MyFollowController
 * @Description TODO
 * @Author GCJ
 * @Date 2020/9/4 14:43
 */
@RestController
@RequestMapping("/myFollow")
public class MyFollowController {
    @Autowired
    private IMyFollowService myFollowService;

    @ApiOperation("我的关注")
    @PostMapping("/saveMyFollowInfo")
    public ResultVo saveMyFollowInfo(@RequestBody MyFollowDto dto){
        return myFollowService.saveMyFollowInfo(dto);
    }

    @ApiOperation("查询我的关注")
    @ApiImplicitParam(name = "userId", value = "ID", required = true, dataType = "String")
    @GetMapping("/queryMyFollowInfoByUserId")
    public ResultVo queryMyFollowInfoByUserId(@PathParam("userId") String userId ){
        return myFollowService.queryMyFollowInfoByUserId(userId);
    }
}
