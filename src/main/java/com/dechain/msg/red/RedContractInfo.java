package com.dechain.msg.red;

import java.math.BigInteger;

public class RedContractInfo {
    private BigInteger len;
    private String contractAddress;
    private String contractName;
    private String contractSymbol;

    public BigInteger getLen() {
        return len;
    }

    public void setLen(BigInteger len) {
        this.len = len;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getContractSymbol() {
        return contractSymbol;
    }

    public void setContractSymbol(String contractSymbol) {
        this.contractSymbol = contractSymbol;
    }
}
