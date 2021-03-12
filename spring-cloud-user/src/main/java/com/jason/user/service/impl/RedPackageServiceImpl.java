package com.jason.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.jason.consts.RedPackageConstant;
import com.jason.domain.ResultVo;
import com.jason.service.RedisCacheService;
import com.jason.user.dto.GetRedPackage;
import com.jason.user.dto.RedPackageDto;
import com.jason.user.service.RedPackageService;
import com.jason.utils.DateUtils;
import com.jason.utils.RedisKeyUtil;
import com.jason.utils.UUidUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @ClassName RedPackageServiceImpl
 * @Description TODO
 * @Author GCJ
 * @Date 2021/3/12 10:10
 */
@Service
public class RedPackageServiceImpl implements RedPackageService {
    @Autowired
    private RedisCacheService redisCacheService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 创建红包
     * @param dto
     */
    @Override
    public ResultVo createRedPackage(RedPackageDto dto) {
        /**
         * 效验参数的合法性
         */
        ResultVo result = voliteRed(dto);

        if(null != result){
            return result;
        }
        //红包id
        dto.setId(UUidUtils.getUUid());
        //发送红包的时间
        dto.setDate(DateUtils.getNowDateStr());

        redisCacheService.put("RED_PACKAGE",dto.getAccount(),JSON.toJSONString(dto));

        int rpCount = dto.getRpCount();

        if(RedPackageConstant.RANDOM_MONEY.equals(dto.getRpType())){
            /**
             * 随机红包
             */
            for (int i=0;i<rpCount;i++){
                stringRedisTemplate.
                        opsForList().
                        leftPush(RedisKeyUtil.RED_PACKAGE+dto.getId(),JSON.toJSONString(randomMoney(dto)));
            }
        }else{
            /**
             * 普通红包
             */
            for (int i=0;i<rpCount;i++){
                stringRedisTemplate.
                        opsForList().
                        leftPush(RedisKeyUtil.RED_PACKAGE+dto.getId(),JSON.toJSONString(commonMoney(dto)));
            }
        }

        return new ResultVo(true,200,"红包创建成功");
    }

    /**
     * 抢红包
     * @param dto
     */
    @Override
    public ResultVo getRedPackageMoney(GetRedPackage dto) {

        if(null == dto){
            return new ResultVo(false,500,"抢红包失败");
        }

        if(StringUtils.isBlank(dto.getAccount())){
            return new ResultVo(false,311,"缺少用户信息");
        }

        if(StringUtils.isBlank(dto.getRpAccount())){
            return new ResultVo(false,312,"缺少红包发布者的信息");
        }
        String money = "";

        synchronized (this){
            money = stringRedisTemplate.opsForList().leftPop(RedisKeyUtil.RED_PACKAGE+dto.getRpId());
            if(null == money){
                return new ResultVo(true,200,"手慢了,红包已经抢完了");
            }
        }

         return new ResultVo(true,200,"恭喜你抢到:"+money+"元");
    }

    /**
     * 计算金额
     * @param red
     * @return
     */
    public BigDecimal randomMoney(RedPackageDto red){
        //当红包剩余个数为0的时候金额全部返回
        if(red.getRpCount() == 1){
            red.setRpCount(red.getRpCount()-1);
            return red.getRpMoney();
        }
        Random r     = new Random();
        //金额最小值
        BigDecimal min   = BigDecimal.valueOf(0.01);
        //金额最大值 = 总金额/ (红包剩余个数 / 2)

        BigDecimal max   = null;

        max = red.getRpMoney().divide(BigDecimal.valueOf(red.getRpCount()/2),2,BigDecimal.ROUND_HALF_DOWN);

        BigDecimal money = new BigDecimal(r.nextDouble() * max.doubleValue()).setScale(2,BigDecimal.ROUND_HALF_UP);

        money = money.compareTo(min)==-1 ? min: money;

        red.setRpMoney(red.getRpMoney().subtract(money));

        red.setRpCount(red.getRpCount()-1);

        return money;
    }
    /**
     * 计算金额
     * @param red
     * @return
     */
    public BigDecimal commonMoney(RedPackageDto red){
        return red.getRpMoney();
    }

    public ResultVo voliteRed(RedPackageDto dto){
        if(null == dto){
            return new ResultVo(false,500,"红包创建失败");
        }

        if(!RedPackageConstant.RANDOM_MONEY.equals(dto.getRpType())
                && !RedPackageConstant.COMMON_MONEY.equals(dto.getRpType())){
            return new ResultVo(false,301,"红包类型不对");
        }

        if(null == dto.getRpMoney()){
            return new ResultVo(false,302,"缺少金额信息");
        }

        if(StringUtils.isBlank(dto.getAccount())){
            return new ResultVo(false,303,"缺少用户信息");
        }

        if(dto.getRpCount() <0 ){
            return new ResultVo(false,304,"红包数量不能小于0");
        }

        if((dto.getRpMoney().compareTo(BigDecimal.valueOf(0.01))) == -1){
            return new ResultVo(false,305,"红包金额不能少于一分");
        }

        double dcount = (double)dto.getRpCount() / 100;
        if(dto.getRpMoney().compareTo(BigDecimal.valueOf(dcount))==-1){
            return new ResultVo(false,306,"红包金额与数量不符，请调整");
        }

        return null;
    }

}
