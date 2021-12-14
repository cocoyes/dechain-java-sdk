package com.dechain.msg;

import org.web3j.abi.TypeReference;

import java.util.List;
import java.util.Map;

public class FunctionResult {
    private String hash;
    private List<TypeReference<?>> result;

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public List<TypeReference<?>> getResult() {
        return result;
    }

    public void setResult(List<TypeReference<?>> result) {
        this.result = result;
    }
}
