package test;

/**
 * @ClassName TestMoreThread
 * @Description TODO
 * @Author GCJ
 * @Date 2020/4/28 10:46
 */

/**
 * 线程数量 = CPU 核数（逻辑）就可以了，但是实际上，数量一般会设置为 CPU 核数（逻辑）+ 1， 为什么呢？
 *
 * 《Java并发编程实战》这么说：
 *
 * 计算(CPU)密集型的线程恰好在某时因为发生一个页错误或者因其他原因而暂停，刚好有一个“额外”的线程，可以确保在这种情况下CPU周期不会中断工作。
 */
public class TestMoreThread {
    private static long count1 = 2000000000;
    private static long count2 = 2000000000;
    private static long count3 = 2000000000;
    private static long count4 = 2000000000;
    private static long count5 = 2000000000;

    private static long result = 0;



    public static void main(String[] args) {
        /**
         * 单线程的情况在 计算200亿需要8000多毫秒
         */
        //singLegon();
        /**
         * 开启5个线程以后 计算200亿需要2000多毫秒
         */
        moreThread();
    }

    private static void moreThread() {
        long begin = System.currentTimeMillis();
        System.out.println("开始时间:"+begin);
        for(int i=0;i<100;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(long i=1;i<=200000000;i++){
                        count1--;
                    }
                    long end = System.currentTimeMillis();
                    System.out.println(Thread.currentThread().getName()+"结束时间:"+end+"用时"+(end - begin));
                }
            }).start();

        }

       /* new Thread(new Runnable() {
            @Override
            public void run() {
                for(long i=1;i<=2000000000;i++){
                    count2--;
                }
                for(long i=1;i<=2000000000;i++){
                    count2--;
                }
                long end = System.currentTimeMillis();
                System.out.println(Thread.currentThread().getName()+"结束时间:"+end);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(long i=1;i<=2000000000;i++){
                    count3--;
                }
                for(long i=1;i<=2000000000;i++){
                    count3--;
                }
                long end = System.currentTimeMillis();
                System.out.println(Thread.currentThread().getName()+"结束时间:"+end);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(long i=1;i<=2000000000;i++){
                    count4--;
                }
                for(long i=1;i<=2000000000;i++){
                    count4--;
                }
                long end = System.currentTimeMillis();
                System.out.println(Thread.currentThread().getName()+"结束时间:"+end);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(long i=1;i<=2000000000;i++){
                    count5--;
                }
                for(long i=1;i<=2000000000;i++){
                    count5--;
                }
                long end = System.currentTimeMillis();
                System.out.println("结束时间:"+end);
                System.out.println("用时:"+(end-begin)+"毫秒");
            }
        }).start();*/

    }

    private static void singLegon() {
        long begin = System.currentTimeMillis();
        System.out.println("开始时间:"+begin);
        for(long i=1;i<=2000000000;i++){
            count1--;
        }
        for(long i=1;i<=2000000000;i++){
            count1--;
        }
        for(long i=1;i<=2000000000;i++){
            count1--;
        }
        for(long i=1;i<=2000000000;i++){
            count1--;
        }
        for(long i=1;i<=2000000000;i++){
            count1--;
        }
        for(long i=1;i<=2000000000;i++){
            count1--;
        }
        for(long i=1;i<=2000000000;i++){
            count2--;
        }
        for(long i=1;i<=2000000000;i++){
            count3--;
        }
        for(long i=1;i<=2000000000;i++){
            count4--;
        }
        for(long i=1;i<=2000000000;i++){
            count5--;
        }
        long end = System.currentTimeMillis();
        System.out.println("结束时间:"+end);
        System.out.println("用时:"+(end-begin)+"毫秒");
    }
}
