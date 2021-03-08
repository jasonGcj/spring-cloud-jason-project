package redpackage;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName Person
 * @Description TODO
 * @Author GCJ
 * @Date 2021/2/27 16:05
 */
public class Person{
    /**
     * id
     */
    private String id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 时间
     */
    private Date start;
    /**
     * 金钱
     */
    private BigDecimal money;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }


    public Person() {
    }

}
