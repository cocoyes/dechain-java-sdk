package com.dechain.msg.pay;


import java.math.BigInteger;

/**
 *  owner,
 *  openStatus,
 *  total,
 *  totalFee,
 *  baseKeepAmount
 *  baseFee
 */
public class PayBaseInfo {
    private String own; //支付管理页
    private Boolean openStatus;  // 全局开关
    private BigInteger total; //交易总次数
    private BigInteger totalFee; //产生总手续费
    private BigInteger baseKeepAmount; // 保证金
    private BigInteger baseFee; // 基础费率

    public String getOwn() {
        return own;
    }

    public Boolean getOpenStatus() {
        return openStatus;
    }

    public void setOpenStatus(Boolean openStatus) {
        this.openStatus = openStatus;
    }

    public void setOwn(String own) {
        this.own = own;
    }



    public BigInteger getTotal() {
        return total;
    }

    public void setTotal(BigInteger total) {
        this.total = total;
    }

    public BigInteger getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigInteger totalFee) {
        this.totalFee = totalFee;
    }

    public BigInteger getBaseKeepAmount() {
        return baseKeepAmount;
    }

    public void setBaseKeepAmount(BigInteger baseKeepAmount) {
        this.baseKeepAmount = baseKeepAmount;
    }

    public BigInteger getBaseFee() {
        return baseFee;
    }

    public void setBaseFee(BigInteger baseFee) {
        this.baseFee = baseFee;
    }
}
