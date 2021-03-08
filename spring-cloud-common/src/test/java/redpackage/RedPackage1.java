package redpackage;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName RedPackage
 * @Description TODO
 * @Author GCJ
 * @Date 2021/2/27 16:07
 */
public class RedPackage1 {
    /**
     * 总金额
     */
    private BigDecimal totalMoney;
    /**
     * 总数量
     */
    private BigDecimal total;
    /**
     * 开始时间
     */
    private Date start;
    /**
     * 结束时间
     */
    private Date end;

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
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

    public BigDecimal getTotal() {
        return total;
    }
}
