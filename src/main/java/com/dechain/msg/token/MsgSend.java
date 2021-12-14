package com.dechain.msg.token;

import com.dechain.msg.MsgBase;
import com.dechain.msg.common.Message;
import com.dechain.msg.common.Token;
import com.dechain.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class MsgSend extends MsgBase {

    public MsgSend() {
        setMsgType("dechain/token/MsgTransfer");
    }

    public static void main(String[] args) {
        MsgSend msg = new MsgSend();

        msg.initMnemonic("puzzle glide follow cruel say burst deliver wild tragic galaxy lumber offer");

        Message messages = msg.produceSendMsg(
                "de",
                "6.00000000",
                "dechain1v853tq96n9ghvyxlvqyxyj97589clccrufrkz9");

        // dechaincli tx send dechain10q0rk5qnyag7wfvvt7rtphlw589m7frsku8qc9 dechain1v853tq96n9ghvyxlvqyxyj97589clccrufrkz9 6de --from captain -y -b block --fees 0.01de
        msg.submit(messages, "0.01000000", "200000", "dechain transfer!");
    }

    public Message produceSendMsg(String denom, String amountDenom, String to) {

        List<Token> amountList = new ArrayList<>();
        Token amount = new Token();
        amount.setDenom(denom);
        amount.setAmount(Utils.NewDecString(amountDenom));
        amountList.add(amount);

        MsgSendValue value = new MsgSendValue();
        value.setFromAddress(this.address);
        value.setToAddress(to);
        value.setAmount(amountList);

        Message<MsgSendValue> msg = new Message<>();
        msg.setType(msgType);
        msg.setValue(value);
        return msg;
    }

}
