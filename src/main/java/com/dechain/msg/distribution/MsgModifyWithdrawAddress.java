package com.dechain.msg.distribution;

import com.dechain.msg.MsgBase;
import com.dechain.msg.common.Message;

public class MsgModifyWithdrawAddress extends MsgBase {

    public MsgModifyWithdrawAddress() {
        setMsgType("dechain/distribution/MsgModifyWithdrawAddress");
    }

    public static void main(String[] args) {
        MsgModifyWithdrawAddress msg = new MsgModifyWithdrawAddress();

        msg.initMnemonic("puzzle glide follow cruel say burst deliver wild tragic galaxy lumber offer");

        Message messages = msg.produceModifyWithdrawAddressMsg(
                "dechain1v853tq96n9ghvyxlvqyxyj97589clccrufrkz9");

        msg.submit(messages, "0.01000000", "200000", "dechain modify withdraw address!");
    }

    public Message produceModifyWithdrawAddressMsg(String withdrawAddress) {

        MsgModifyWithdrawAddressValue value = new MsgModifyWithdrawAddressValue();
        value.setDelegatorAddress(this.address);
        value.setWithdrawAddress(withdrawAddress);

        Message<MsgModifyWithdrawAddressValue> msg = new Message<>();
        msg.setType(msgType);
        msg.setValue(value);
        return msg;
    }

}
