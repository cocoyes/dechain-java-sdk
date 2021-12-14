package com.dechain.msg.token;

import com.dechain.msg.MsgBase;
import com.dechain.msg.common.Message;
import com.dechain.msg.common.Token;
import com.dechain.utils.Utils;

public class MsgTokenBurn extends MsgBase {

    public MsgTokenBurn() { setMsgType("dechain/token/MsgBurn");}

    public Message produceTokenBurnMsg(String denom, String amountDenom, String owner) {

        Token amount = new Token();
        amount.setDenom(denom);
        amount.setAmount(Utils.NewDecString(amountDenom));

        MsgTokenBurnValue value = new MsgTokenBurnValue();
        value.setAmount(amount);
        value.setOwner(owner);

        Message<MsgTokenBurnValue> msg = new Message<>();
        msg.setType(msgType);
        msg.setValue(value);

        return msg;
    }

}
