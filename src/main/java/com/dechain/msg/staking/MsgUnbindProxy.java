package com.dechain.msg.staking;

import com.dechain.msg.MsgBase;
import com.dechain.msg.common.Message;

public class MsgUnbindProxy extends MsgBase {

    public MsgUnbindProxy() { setMsgType("dechain/staking/MsgUnbindProxy"); }

    public Message produce() {

        MsgUnbindProxyValue value = new MsgUnbindProxyValue();

        value.setDelegatorAddress(this.address);

        Message<MsgUnbindProxyValue> msg = new Message<>();
        msg.setType(msgType);
        msg.setValue(value);
        return msg;
    }
}
