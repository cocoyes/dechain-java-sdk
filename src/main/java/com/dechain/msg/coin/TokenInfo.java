package com.dechain.msg.coin;

public class TokenInfo {
    /**
     * 0: address: owner 0x3901952De2f16ad9B8646CF59C337d0b445A81Ca
     * 1: address: token 0x3901952De2f16ad9B8646CF59C337d0b445A81Ca
     * 2: string: symbol EEE
     * 3: string: icon ADAFAFA
     * 4: uint256: len 18
     * 5: string: tokenName AAA
     * 6: bool: used true
     * 7: uint256: sort 0
     * 8: bool: status false
     */
    private String owner; //发行人地址
    private String token; //合约地址
    private String symbol; //合约符号
    private String icon; //合约logo
    private int len; //合约精度
    private String tokenName; //合约名称
    private int sort; //排序
    private boolean status; //状态
    private String red; //红包合约地址
    private String pay; //支付合约地址

    public String getRed() {
        return red;
    }

    public void setRed(String red) {
        this.red = red;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public String getTokenName() {
        return tokenName;
    }

    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
