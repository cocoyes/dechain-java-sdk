package com.dechain.msg.staking;

import com.dechain.msg.MsgBase;
import com.dechain.msg.common.Message;

public class MsgDestroyValidator extends MsgBase {

    public MsgDestroyValidator() { setMsgType("dechain/staking/MsgDestroyValidator"); }

    public Message produceMsg() {

        MsgDestroyValidatorValue value = new MsgDestroyValidatorValue();

        value.setDelegatorAddress(this.address);

        Message<MsgDestroyValidatorValue> msg = new Message<>();
        msg.setType(msgType);
        msg.setValue(value);
        return msg;
    }
}
