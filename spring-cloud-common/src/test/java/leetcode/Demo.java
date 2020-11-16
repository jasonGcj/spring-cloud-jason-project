package leetcode;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Demo
 * @Description TODO
 * @Author GCJ
 * @Date 2020/11/10 16:38
 */
@SpringBootTest
public class Demo {

    /**
     *  判定字符是否唯一
     *  实现一个算法，确定一个字符串 s 的所有字符是否全都不同。
     *
     * 示例 1：
     *
     * 输入: s = "leetcode"
     * 输出: false
     * 示例 2：
     *
     * 输入: s = "abc"
     * 输出: true
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    @Test
    public void isUnique(){
        String str = "leetcode";
        Map<Object, Integer> map = new HashMap<>();
        for(int i=0;i<str.length();i++){
            char c = str.charAt(i);
            Integer integer = map.get(c);
            if(integer !=null){
                if(integer>=1){
                    System.out.println(false);
                    return;
                }
            }else{
                map.put(c,1);
            }
        }
        System.out.println(true);
    }

    /**
     * 给定两个字符串 s1 和 s2，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。
     *
     * 示例 1：
     *
     * 输入: s1 = "abc", s2 = "bca"
     * 输出: true
     * 示例 2：
     *
     * 输入: s1 = "abc", s2 = "bad"
     * 输出: false
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/check-permutation-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    @Test
    public void CheckPermutation(){
        String s1 = "abc", s2 = "bca";
        /*if(s1.length()!=s2.length()){
            System.out.println("不等于");
            return;
        }
        char[] chars = s1.toCharArray();
        char[] chars1 = s2.toCharArray();
        Map<Object, Integer> map = new HashMap<>();
        Map<Object, Integer> map1 = new HashMap<>();
        for (char aChar : chars) {
            Integer integer = map.get(aChar);
            if(integer == null){
                map.put(aChar,1);
            }else{
                Integer a =1+integer;
                map.put(aChar,a);
            }
        }
        for (char aChar : chars1) {
            Integer integer = map1.get(aChar);
            if(integer == null){
                map1.put(aChar,1);
            }else{
                Integer a =1+integer;
                map1.put(aChar,a);
            }
        }
        for (char aChar : chars) {
            boolean b = map1.get(aChar) == map.get(aChar);
            if(!b){
                System.out.println(false);
                return;
            }
        }*/

        int sum1 = 0;
        int sum2 = 0;
        if(s1.length() != s2.length()) return ;
        else{
            for(int i=0; i<s1.length(); i++){
                sum1 += s1.charAt(i);
                sum2 += s2.charAt(i);
            }
        }

        System.out.println((sum1 == sum2));
    }

    /**
     * URL化。编写一种方法，将字符串中的空格全部替换为%20。假定该字符串尾部有足够的空间存放新增字符，并且知道字符串的“真实”长度。（注：用Java实现的话，请使用字符数组实现，以便直接在数组上操作。）
     *
     *  
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/string-to-url-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    @Test
    public void replaceSpaces(){
        String str = "\"Mr John Smith    \", 13";
        String sb = str.substring(0, str.lastIndexOf("\""));
        String[] split = sb.split("");
        String stt = "";
        for (String s : split) {
            if(!" ".equals(s)){
                stt+=s;
            }else{
                stt+="20%";
            }
        }
        System.out.println(stt);
    }

    @Test
    public void run() throws Exception {
        new Foo().first(new Runnable() {
            @Override
            public void run() {
                System.out.println("first");
            }
        });

        new Foo().second(new Runnable() {
            @Override
            public void run() {
                System.out.println("second");
            }
        });

        new Foo().third(new Runnable() {
            @Override
            public void run() {
                System.out.println("third");
            }
        });
    }

}

class Foo{
    public Foo() {

    }


    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
    }

    public void second(Runnable printSecond) throws InterruptedException {

        Thread.sleep(500);
        printSecond.run();
    }

    public void third(Runnable printThird) throws InterruptedException {

        Thread.sleep(1000);
        printThird.run();
    }
}
