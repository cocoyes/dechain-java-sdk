package com.dechain.msg.token;

import com.dechain.msg.MsgBase;
import com.dechain.msg.common.Message;

public class MsgConfirmOwnership extends MsgBase {

    public MsgConfirmOwnership() { setMsgType("dechain/token/MsgConfirmOwnership"); }

    public Message produceConfirmOwnershipMsg(String address, String symbol) {

        MsgConfirmOwnershipValue value = new MsgConfirmOwnershipValue();
        value.setAddress(address);
        value.setSymbol(symbol);

        Message<MsgConfirmOwnershipValue> msg = new Message<>();
        msg.setType(msgType);
        msg.setValue(value);

        return msg;
    }

}
