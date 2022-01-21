package com.dechain.msg.coin;

import java.math.BigInteger;

public class RegisterBaseInfo {
    private BigInteger registerTokenFee;
    private BigInteger registerRedFee;
    private BigInteger registerPayFee;
    private BigInteger openContractCount;
    private BigInteger openRedCount;
    private BigInteger openPayCount;

    public BigInteger getRegisterTokenFee() {
        return registerTokenFee;
    }

    public void setRegisterTokenFee(BigInteger registerTokenFee) {
        this.registerTokenFee = registerTokenFee;
    }

    public BigInteger getRegisterRedFee() {
        return registerRedFee;
    }

    public void setRegisterRedFee(BigInteger registerRedFee) {
        this.registerRedFee = registerRedFee;
    }

    public BigInteger getRegisterPayFee() {
        return registerPayFee;
    }

    public void setRegisterPayFee(BigInteger registerPayFee) {
        this.registerPayFee = registerPayFee;
    }

    public BigInteger getOpenContractCount() {
        return openContractCount;
    }

    public void setOpenContractCount(BigInteger openContractCount) {
        this.openContractCount = openContractCount;
    }

    public BigInteger getOpenRedCount() {
        return openRedCount;
    }

    public void setOpenRedCount(BigInteger openRedCount) {
        this.openRedCount = openRedCount;
    }

    public BigInteger getOpenPayCount() {
        return openPayCount;
    }

    public void setOpenPayCount(BigInteger openPayCount) {
        this.openPayCount = openPayCount;
    }
}
