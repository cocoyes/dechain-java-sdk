package com.dechain.msg.dex;

import com.dechain.env.EnvInstance;
import com.dechain.msg.MsgBase;
import com.dechain.msg.common.Message;
import com.dechain.utils.Utils;

public class MsgList extends MsgBase {

    public MsgList() {
        setMsgType("dechain/dex/MsgList");
    }

    public static void main(String[] args) {
        EnvInstance.getEnv().setChainID("dechainevm-8");
        EnvInstance.getEnv().setRestServerUrl("http://localhost:8545");

        MsgList msg = new MsgList();
        msg.initMnemonic("giggle sibling fun arrow elevator spoon blood grocery laugh tortoise culture tool");

        Message messages = msg.produceListMsg(
                "usdk-017",
                "de",
                "1.00000000");

        // dechaincli tx dex list --from captain --base-asset eos-a99 --quote-asset de -y -b block --fees 0.01de
        msg.submit(messages, "0.05000000", "500000", "dechain dex list!");
    }

    public Message produceListMsg(String listAsset, String quoteAsset, String initPrice) {

        MsgListValue value = new MsgListValue();
        value.setOwner(this.address);
        value.setListAsset(listAsset);
        value.setQuoteAsset(quoteAsset);
        value.setInitPrice(Utils.NewDecString(initPrice));

        Message<MsgListValue> msg = new Message<>();
        msg.setType(msgType);
        msg.setValue(value);
        return msg;
    }
}
