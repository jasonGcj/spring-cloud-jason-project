package com.jason.user.timer;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @ClassName UserTimer
 * @Description TODO
 * @Author GCJ
 * @Date 2019/11/20 15:27
 */
@Component
@EnableScheduling
public class UserTimer {
    //@Scheduled(cron = "0/1 * * * * ? ")
    public void  aa(){
        System.out.println("第一次");
    }
}
