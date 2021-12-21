package com.dechain.msg.coin;

import java.math.BigDecimal;

public class BaseMsg {
    //如果红包数为100个，实际需要花费大于255万gas,抢红包只需要小于15万即可，按实际消耗决定
    //以下单次最多花费手续费0.00000001
    public static final BigDecimal GAS_LIMIT=new BigDecimal(5000000);
    public static final BigDecimal GAS_PRICE=new BigDecimal(2000);

    private boolean success;
    private String hash;
    private String msg;

    public static BaseMsg buildError(String msg){
        BaseMsg dto=new BaseMsg();
        dto.setSuccess(false);
        dto.setMsg(msg);
        return dto;
    }
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
