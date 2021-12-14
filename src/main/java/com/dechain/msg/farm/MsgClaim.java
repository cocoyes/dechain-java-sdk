package com.dechain.msg.farm;

import com.alibaba.fastjson.JSONObject;
import com.dechain.env.EnvInstance;
import com.dechain.msg.MsgBase;
import com.dechain.msg.common.Message;
import com.dechain.utils.crypto.PrivateKey;

public class MsgClaim extends MsgBase {

    public MsgClaim() { setMsgType("dechain/farm/MsgClaim"); }

    public Message produceMsg(String poolName) {

        MsgClaimValue value = new MsgClaimValue();

        value.setAddress(this.address);
        value.setPoolName(poolName);

        Message<MsgClaimValue> msg = new Message<>();
        msg.setType(msgType);
        msg.setValue(value);
        return msg;
    }

    public static void main(String[] args) {

        EnvInstance.getEnv().setChainID("dechainevm-8");
        EnvInstance.getEnv().setRestServerUrl("http://localhost:8545");

        PrivateKey key = new PrivateKey("EA6D97F31E4B70663594DD6AFC3E3550AAB5FDD9C44305E8F8F2003023B27FDA");

        MsgClaim msg = new MsgClaim();
        msg.init(key);

        Message messages = msg.produceMsg(
                "turing-pool-1"
        );
        JSONObject res = msg.submit(messages, "0.05", "500000", "claim");

        try {
            boolean succeed = msg.isTxSucceed(res);
            System.out.println("tx " + (succeed ? "succeed": "failed"));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
