package com.dechain.msg.pay;

import java.math.BigInteger;

public class OrderInfo {
    /**
     * 0: string: oid
     * 1: uint256: amount 300000000000000000000
     * 2: uint256: status 1
     * 3: address: payUser 0x3901952De2f16ad9B8646CF59C337d0b445A81Ca
     * 4: uint256: block 128719
     * 5: bool: used true
     * 6: address: business 0x3901952De2f16ad9B8646CF59C337d0b445A81Ca
     */
    private String oid; //订单号
    private BigInteger amount;  // 下单数量
    private BigInteger status;  //状态 0未支付，1已支付，撤单订单不存在
    private String payUser; //支付人地址
    private BigInteger block; //订单下单区块数
    private String business; //商家地址

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public BigInteger getAmount() {
        return amount;
    }

    public void setAmount(BigInteger amount) {
        this.amount = amount;
    }

    public BigInteger getStatus() {
        return status;
    }

    public void setStatus(BigInteger status) {
        this.status = status;
    }

    public String getPayUser() {
        return payUser;
    }

    public void setPayUser(String payUser) {
        this.payUser = payUser;
    }

    public BigInteger getBlock() {
        return block;
    }

    public void setBlock(BigInteger block) {
        this.block = block;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }
}
