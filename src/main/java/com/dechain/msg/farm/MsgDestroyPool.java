package com.dechain.msg.farm;

import com.alibaba.fastjson.JSONObject;
import com.dechain.env.EnvInstance;
import com.dechain.msg.MsgBase;
import com.dechain.msg.ammswap.MsgAddLiquidityValue;
import com.dechain.msg.common.Message;
import com.dechain.utils.crypto.PrivateKey;

public class MsgDestroyPool extends MsgBase {

    public MsgDestroyPool() { setMsgType("dechain/farm/MsgDestroyPool"); }

    public Message produceMsg(String poolName) {

        MsgDestroyPoolValue value = new MsgDestroyPoolValue();

        value.setOwner(this.address);
        value.setPoolName(poolName);

        Message<MsgDestroyPoolValue> msg = new Message<>();
        msg.setType(msgType);
        msg.setValue(value);
        return msg;
    }

    public static void main(String[] args) {

        EnvInstance.getEnv().setChainID("dechainevm-8");
        EnvInstance.getEnv().setRestServerUrl("http://localhost:8545");

        PrivateKey key = new PrivateKey("EA6D97F31E4B70663594DD6AFC3E3550AAB5FDD9C44305E8F8F2003023B27FDA");

        MsgDestroyPool msg = new MsgDestroyPool();
        msg.init(key);

        Message messages = msg.produceMsg(
                "turing_pool"
        );
        JSONObject res = msg.submit(messages, "0.05", "500000", "destroy pool!");

        try {
            boolean succeed = msg.isTxSucceed(res);
            System.out.println("tx " + (succeed ? "succeed": "failed"));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
