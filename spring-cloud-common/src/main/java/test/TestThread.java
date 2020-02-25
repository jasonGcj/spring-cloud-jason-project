package test;

/**
 * @ClassName TestThread
 * @Description TODO
 * 子线程循环10次，接着主线程循环100次，接着又回到子线程循环10次，接着再回到主线程100次，如此循环50次
 * @Author GCJ
 * @Date 2020/2/25 14:45
 */
public class TestThread {

    private static Object object = new Object();

    public static void main(String[] args) throws InterruptedException {
        //创建子线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=1;i<=50;i++){

                    synchronized (object){
                        for(int j=1;j<=10;j++){
                           // System.out.println("子线程正在执行:...."+j);
                        }
                        System.out.println("子线程执行的第"+i+"个10次");
                        object.notifyAll();
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }


            }
        }).start();

        for (int i=1;i<=50;i++){
            synchronized (object){
                //主线程让出锁，等待子线程唤醒
                object.wait();
                for(int j=1;j<=15;j++){
                   // System.out.println("主线程正在执行:...."+j);
                }
                System.out.println("主线程执行的第"+i+"个15次");
                object.notifyAll();
            }

        }
    }
}
