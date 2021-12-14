package com.dechain.msg.dex;

import com.dechain.msg.MsgBase;
import com.dechain.msg.common.Message;
import com.dechain.msg.common.Token;
import com.dechain.utils.Utils;

public class MsgWithdrawDeposit extends MsgBase {

    public MsgWithdrawDeposit() { setMsgType("dechain/dex/MsgWithdraw"); }

    public Message produce(String denom, String amountDenom, String product) {

        Token amount = new Token();
        amount.setDenom(denom);
        amount.setAmount(Utils.NewDecString(amountDenom));

        MsgWithdrawDepositValue value = new MsgWithdrawDepositValue();

        value.setDepositor(this.address);
        value.setAmount(amount);
        value.setProduct(product);

        Message<MsgWithdrawDepositValue> msg = new Message<>();
        msg.setType(msgType);
        msg.setValue(value);
        return msg;
    }
}
