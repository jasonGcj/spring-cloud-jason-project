package redpackage;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Random;

/**
 * @ClassName Test
 * @Description TODO
 * @Author GCJ
 * @Date 2021/2/27 16:09
 */
public class RedTest {

    public static double getRandomMoney(RedPackage red) {
        // remainSize 剩余的红包数量
        // remainMoney 剩余的钱
        if (red.getTotal() == 1) {
            red.setTotal(red.getTotal()-1);
            return (double) Math.round(red.getTotalMoney() * 100) / 100;
        }
        Random r     = new Random();
        double min   = 0.01; //
        double max   = red.getTotalMoney() / red.getTotal() * 2;
        double money = r.nextDouble() * max;
        money = money <= min ? 0.01: money;
        money = Math.floor(money * 100) / 100;
        red.setTotalMoney(red.getTotalMoney()-money);
        return money;
    }

    public static BigDecimal getRandomMoney2(RedPackage1 red) {
        // remainSize 剩余的红包数量
        // remainMoney 剩余的钱
        if(red.getTotal().compareTo(BigDecimal.valueOf(0)) == 0){
            return BigDecimal.valueOf(0);
        }

        if(red.getTotal().compareTo(BigDecimal.valueOf(1)) == 0){
            red.setTotal(red.getTotal().subtract(BigDecimal.valueOf(1)));
            return red.getTotalMoney();
        }
        Random r     = new Random();
        BigDecimal min   = BigDecimal.valueOf(0.01); //
        BigDecimal max   = red.getTotalMoney().divide(red.getTotal().multiply(BigDecimal.valueOf(2)),2,BigDecimal.ROUND_HALF_UP);
        BigDecimal money = BigDecimal.valueOf(r.nextDouble()).multiply(max);
        money = money.compareTo(min)==-1 ? min: money;
        red.setTotalMoney(red.getTotalMoney().subtract(money));
        return money;
    }

    @Test
    public void testRed(){
        RedPackage redPackage = new RedPackage();
        redPackage.setTotalMoney(5);
        redPackage.setTotal(5);
        for(int i =0;i<5;i++){
            new Runnable() {
                @Override
                public void run() {
                    double randomMoney = getRandomMoney(redPackage);
                    redPackage.setTotal(redPackage.getTotal()-1);
                    System.out.println(Thread.currentThread().getName()+"："+randomMoney);
                }
            }.run();
        }
    }


    public static void main(String[] args) {
        RedPackage1 redPackage = new RedPackage1();
        redPackage.setTotalMoney(BigDecimal.valueOf(5));
        redPackage.setTotal(BigDecimal.valueOf(5));
        for(int i =0;i<5;i++){
            new Runnable() {
                @Override
                public void run() {
                    BigDecimal randomMoney2 = getRandomMoney2(redPackage).setScale(2,BigDecimal.ROUND_HALF_UP);
                    redPackage.setTotal(redPackage.getTotal().subtract(BigDecimal.valueOf(1)));
                    System.out.println(Thread.currentThread().getName()+"："+randomMoney2);
                }
            }.run();
        }
    }



}
