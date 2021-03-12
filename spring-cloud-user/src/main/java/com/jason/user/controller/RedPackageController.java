package com.jason.user.controller;

import com.jason.domain.ResultVo;
import com.jason.user.dto.GetRedPackage;
import com.jason.user.dto.RedPackageDto;
import com.jason.user.service.RedPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName RedPackageController
 * @Description TODO
 * @Author GCJ
 * @Date 2021/3/12 10:08
 */
@RestController
@RequestMapping("/redPackage")
public class RedPackageController {

    @Autowired
    private RedPackageService redPackageService;

    @RequestMapping("/createRedPackage")
    public ResultVo createRedPackage(@RequestBody RedPackageDto dto){
        return redPackageService.createRedPackage(dto);
    }

    @RequestMapping("/getRedPackageMoney")
    public ResultVo getRedPackageMoney(@RequestBody GetRedPackage dto){
        return redPackageService.getRedPackageMoney(dto);
    }

}
