package com.dechain.msg.token;

import com.dechain.msg.MsgBase;
import com.dechain.msg.common.Message;
import com.dechain.msg.common.Token;
import com.dechain.utils.Utils;

public class MsgTokenMint extends MsgBase {

    public MsgTokenMint() { setMsgType("dechain/token/MsgMint");}

    public Message produceTokenMintMsg(String denom, String amountDenom, String owner) {

        Token amount = new Token();
        amount.setDenom(denom);
        amount.setAmount(Utils.NewDecString(amountDenom));

        MsgTokenMintValue value = new MsgTokenMintValue();
        value.setAmount(amount);
        value.setOwner(owner);

        Message<MsgTokenMintValue> msg = new Message<>();
        msg.setType(msgType);
        msg.setValue(value);

        return msg;
    }

}
