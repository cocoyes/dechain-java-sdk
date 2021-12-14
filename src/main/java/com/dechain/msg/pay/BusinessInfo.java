package com.dechain.msg.pay;

import java.math.BigInteger;

public class BusinessInfo {
    private String address; //商家地址
    private String icon;  // 商家图标
    private String name;  //商家名称
    private BigInteger status;  // 状态
    private BigInteger balance; // 商家入账余额
    private BigInteger count;  //商家入账笔数
    private BigInteger fee; //商家手续费
    private BigInteger totalFeeBalance; //商家产生的手续费
    private BigInteger keepBalance; //商家保证金
    private Boolean used; //是否可用

    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getStatus() {
        return status;
    }

    public void setStatus(BigInteger status) {
        this.status = status;
    }

    public BigInteger getBalance() {
        return balance;
    }

    public void setBalance(BigInteger balance) {
        this.balance = balance;
    }

    public BigInteger getCount() {
        return count;
    }

    public void setCount(BigInteger count) {
        this.count = count;
    }

    public BigInteger getFee() {
        return fee;
    }

    public void setFee(BigInteger fee) {
        this.fee = fee;
    }

    public BigInteger getTotalFeeBalance() {
        return totalFeeBalance;
    }

    public void setTotalFeeBalance(BigInteger totalFeeBalance) {
        this.totalFeeBalance = totalFeeBalance;
    }

    public BigInteger getKeepBalance() {
        return keepBalance;
    }

    public void setKeepBalance(BigInteger keepBalance) {
        this.keepBalance = keepBalance;
    }
}
