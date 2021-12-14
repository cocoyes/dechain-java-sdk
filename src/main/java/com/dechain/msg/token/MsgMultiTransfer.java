package com.dechain.msg.token;

import com.dechain.msg.MsgBase;
import com.dechain.msg.common.Message;
import com.dechain.msg.common.TransferUnit;

import java.util.List;

public class MsgMultiTransfer extends MsgBase {

    public MsgMultiTransfer() { setMsgType("dechain/token/MsgMultiTransfer"); }

    public Message produceMsg(List<TransferUnit> transfers) {

        MsgMultiTransferValue value = new MsgMultiTransferValue();

        value.setFrom(this.address);
        value.setTransfers(transfers);

        Message<MsgMultiTransferValue> msg = new Message<>();
        msg.setType(msgType);
        msg.setValue(value);
        return msg;
    }
}
