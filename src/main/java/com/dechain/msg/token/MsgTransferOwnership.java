package com.dechain.msg.token;

import com.dechain.msg.MsgBase;
import com.dechain.msg.common.Message;

public class MsgTransferOwnership extends MsgBase {

    public MsgTransferOwnership() { setMsgType("dechain/token/MsgTransferOwnership");}

    public Message produceTransferOwnerShipMsg(String fromAddress, String toAddress, String symbol) {

        MsgTransferOwnershipValue value = new MsgTransferOwnershipValue();
        value.setFromAddress(fromAddress);
        value.setToAddress(toAddress);
        value.setSymbol(symbol);

        Message<MsgTransferOwnershipValue> msg = new Message<>();
        msg.setType(msgType);
        msg.setValue(value);

        return msg;
    }

}
