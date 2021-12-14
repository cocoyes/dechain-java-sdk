package com.dechain.msg.dex;

import com.dechain.msg.MsgBase;
import com.dechain.msg.common.Message;

public class MsgTransferTokenPairOwnership extends MsgBase {

    public MsgTransferTokenPairOwnership () {
        setMsgType("dechain/dex/MsgTransferTradingPairOwnership");
    }
    public Message produceTransferTokenPairOwnershipMsg (String fromAddress, String toAddress, String product) {

        MsgTransferTokenPairOwnershipValue value = new MsgTransferTokenPairOwnershipValue();
        value.setFromAddress(fromAddress);
        value.setProduct(product);
        value.setToAddress(toAddress);

        Message<MsgTransferTokenPairOwnershipValue> msg = new Message<>();
        msg.setType(msgType);
        msg.setValue(value);
        return msg;

    }
}
