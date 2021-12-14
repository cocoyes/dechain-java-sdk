package com.dechain.msg.red;

import java.math.BigInteger;
import java.util.List;

public class RedPackInfo {
    private String rid;
    private BigInteger amount; // 总金额
    private BigInteger balance; //余额
    private BigInteger count;
    private List<String> address; //抢的地址列表
    private List<BigInteger> pickAmount;  //对照地址列表的抢的金额


    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public BigInteger getAmount() {
        return amount;
    }

    public void setAmount(BigInteger amount) {
        this.amount = amount;
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

    public List<String> getAddress() {
        return address;
    }

    public void setAddress(List<String> address) {
        this.address = address;
    }

    public List<BigInteger> getPickAmount() {
        return pickAmount;
    }

    public void setPickAmount(List<BigInteger> pickAmount) {
        this.pickAmount = pickAmount;
    }
}
