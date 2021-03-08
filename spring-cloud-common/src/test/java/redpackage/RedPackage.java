package redpackage;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName RedPackage
 * @Description TODO
 * @Author GCJ
 * @Date 2021/2/27 16:07
 */
public class RedPackage {
    /**
     * 总金额
     */
    private double totalMoney;
    /**
     * 总数量
     */
    private int total;
    /**
     * 开始时间
     */
    private Date start;
    /**
     * 结束时间
     */
    private Date end;

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
