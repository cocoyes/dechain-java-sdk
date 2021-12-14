package com.dechain.msg.tx;

import com.dechain.msg.common.TxValue;
import com.dechain.utils.Utils;


public class BroadcastValue {

    private String type = "cosmos-sdk/StdTx";
    private TxValue value;

    public BroadcastValue() {
    }

    public TxValue getTx() {
        return value;
    }

    public void setTx(TxValue tx) {
        this.value = tx;
    }

    public String toJson() {
        return Utils.serializer.toJson(this);
    }

    public static BroadcastValue fromJson(String json) {
        return Utils.serializer.fromJson(json, BroadcastValue.class);
    }
}
