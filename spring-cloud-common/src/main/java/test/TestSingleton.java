package test;

/**
 * @ClassName TestSingleton
 * @Description TODO
 * @Author GCJ
 * @Date 2020/4/27 14:32
 */
public class TestSingleton {

    private static TestSingleton singLeton = null;

    /**
     *懒汉模式单例 线程不安全
     */
    public static TestSingleton getTestSingLeton(){

        if(null == singLeton){
            singLeton = new TestSingleton();
        }
        return singLeton;

    }

    public static TestSingleton getTestSingLetonLock(){
            if(null == singLeton){
                synchronized (TestSingleton.class){
                    singLeton = new TestSingleton();
                }
            }
        return singLeton;

    }
}

class Test{

}
