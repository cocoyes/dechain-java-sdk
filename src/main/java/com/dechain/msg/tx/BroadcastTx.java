package com.dechain.msg.tx;

import com.dechain.utils.Utils;
import com.dechain.msg.common.TxValue;


public class BroadcastTx {

    private String mode;
    private String type = "cosmos-sdk/StdTx";
    private TxValue tx;

    public BroadcastTx() {
    }

    public TxValue getTx() {
        return tx;
    }

    public void setTx(TxValue value) {
        this.tx = value;
    }

    public String toJson() {
        return Utils.serializer.toJson(this);
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public static BroadcastTx fromJson(String json) {
        return Utils.serializer.fromJson(json, BroadcastTx.class);
    }
}
