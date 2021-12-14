package com.dechain.msg.gov;

import com.alibaba.fastjson.JSONObject;
import com.dechain.env.EnvInstance;
import com.dechain.msg.MsgBase;
import com.dechain.msg.common.Message;
import com.dechain.msg.common.Token;
import com.dechain.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class MsgDeposit extends MsgBase {

    public MsgDeposit() {
        setMsgType("dechain/gov/MsgDeposit");
    }

    public static void main(String[] args) throws Exception {
        EnvInstance.getEnv().setChainID("dechainevm-8");
        EnvInstance.getEnv().setRestServerUrl("http://localhost:8545");

        MsgDeposit msg = new MsgDeposit();

        msg.initMnemonic("giggle sibling fun arrow elevator spoon blood grocery laugh tortoise culture tool");

        List<Token> amountList = new ArrayList<>();
        Token amount = new Token();
        amount.setDenom(EnvInstance.getEnv().GetDenom());
        amount.setAmount(Utils.NewDecString("100"));
        amountList.add(amount);

        Message messages = msg.produceDepositMsg(
                "2",
                amountList);

        JSONObject res = msg.submit(messages, "0.05000000", "500000", "dechain gov vot!");
        boolean succeed = msg.isTxSucceed(res);
        System.out.println("deposit " + (succeed ? "succeed" : "failed"));
    }

    public Message produceDepositMsg(String ProposalID, List<Token> amount) {

        MsgDepositValue value = new MsgDepositValue();
        value.setProposalID(ProposalID);
        value.setDepositor(this.address);
        value.setAmount(amount);

        Message<MsgDepositValue> msg = new Message<>();
        msg.setType(msgType);
        msg.setValue(value);
        return msg;
    }

}
