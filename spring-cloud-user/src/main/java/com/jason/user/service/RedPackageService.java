package com.jason.user.service;

import com.jason.domain.ResultVo;
import com.jason.user.dto.GetRedPackage;
import com.jason.user.dto.RedPackageDto;

/**
 * @ClassName RedPackageService
 * @Description TODO
 * @Author GCJ
 * @Date 2021/3/12 10:09
 */
public interface RedPackageService {
    /**
     * 创建红包
     * @param dto
     * @return
     */
    ResultVo createRedPackage(RedPackageDto dto);
    /**
     * 抢红包
     * @param dto
     * @return
     */
    ResultVo getRedPackageMoney(GetRedPackage dto);
}
