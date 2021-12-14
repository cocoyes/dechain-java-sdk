package com.dechain.msg.farm;

import com.alibaba.fastjson.JSONObject;
import com.dechain.env.EnvInstance;
import com.dechain.msg.MsgBase;
import com.dechain.msg.common.Message;
import com.dechain.msg.common.Token;
import com.dechain.utils.Utils;
import com.dechain.utils.crypto.PrivateKey;

public class MsgUnlock extends MsgBase {

    public MsgUnlock() { setMsgType("dechain/farm/MsgUnlock"); }

    public Message produceMsg(String amount, String denom, String poolName) {

        Token token = new Token();
        token.setAmount(Utils.NewDecString(amount));
        token.setDenom(denom);

        MsgUnlockValue value = new MsgUnlockValue();

        value.setAddress(this.address);
        value.setAmount(token);
        value.setPoolName(poolName);

        Message<MsgUnlockValue> msg = new Message<>();
        msg.setType(msgType);
        msg.setValue(value);
        return msg;
    }

    public static void main(String[] args) {

        EnvInstance.getEnv().setChainID("dechainevm-8");
        EnvInstance.getEnv().setRestServerUrl("http://localhost:8545");

        PrivateKey key = new PrivateKey("3040196C06C630C1E30D6D347B097C9EA64ADA24FB94823B6C755194F3A00761");

        MsgUnlock msg = new MsgUnlock();
        msg.init(key);

        Message messages = msg.produceMsg(
                "5",
                "de",
                "turing-pool-1"
        );
        JSONObject res = msg.submit(messages, "0.05", "500000", "lock asset!");

        try {
            boolean succeed = msg.isTxSucceed(res);
            System.out.println("tx " + (succeed ? "succeed": "failed"));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
