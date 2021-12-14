package com.dechain.msg.coin;

public class BaseMsg {
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
