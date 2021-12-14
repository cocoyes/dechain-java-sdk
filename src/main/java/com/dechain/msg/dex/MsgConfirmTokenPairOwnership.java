package com.dechain.msg.dex;

import com.dechain.msg.MsgBase;
import com.dechain.msg.common.Message;

public class MsgConfirmTokenPairOwnership extends MsgBase {

    public MsgConfirmTokenPairOwnership () {
        setMsgType("dechain/dex/MsgConfirmOwnership");
    }

    public Message produceConfirmTokenPairOwnershipMsg (String fromAddress, String product) {

        MsgConfirmTokenPairOwnershipValue value = new MsgConfirmTokenPairOwnershipValue();
        value.setFromAddress(fromAddress);
        value.setProduct(product);

        Message<MsgConfirmTokenPairOwnershipValue> msg = new Message<>();
        msg.setType(msgType);
        msg.setValue(value);
        return msg;

    }
}
