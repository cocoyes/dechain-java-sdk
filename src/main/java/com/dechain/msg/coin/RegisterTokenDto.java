package com.dechain.msg.coin;

public class RegisterTokenDto {
    private boolean success;
    private String contract;
    private String msg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }



    public static RegisterTokenDto buildError(String msg){
        RegisterTokenDto dto=new RegisterTokenDto();
        dto.setSuccess(false);
        dto.setMsg(msg);
        return dto;
    }
}
