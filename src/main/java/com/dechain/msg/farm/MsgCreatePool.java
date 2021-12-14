package com.dechain.msg.farm;

import com.alibaba.fastjson.JSONObject;
import com.dechain.env.EnvInstance;
import com.dechain.msg.MsgBase;
import com.dechain.msg.common.Message;
import com.dechain.msg.common.Token;
import com.dechain.utils.Utils;
import com.dechain.utils.crypto.PrivateKey;

public class MsgCreatePool extends MsgBase {

    public MsgCreatePool() { setMsgType("dechain/farm/MsgCreatePool"); }

    public Message produceMsg(String minLockAmount, String minLockDenom, String poolName, String yieldedSymbol) {

        Token minLockAmountToken = new Token();
        minLockAmountToken.setAmount(Utils.NewDecString(minLockAmount));
        minLockAmountToken.setDenom(minLockDenom);

        MsgCreatePoolValue value = new MsgCreatePoolValue();

        value.setMinLockAmount(minLockAmountToken);
        value.setOwner(this.address);
        value.setPoolName(poolName);
        value.setYieldedSymbol(yieldedSymbol);

        Message<MsgCreatePoolValue> msg = new Message<>();
        msg.setType(msgType);
        msg.setValue(value);
        return msg;
    }

    public static void main(String[] args) {

        EnvInstance.getEnv().setChainID("dechainevm-8");
        EnvInstance.getEnv().setRestServerUrl("http://localhost:8545");

        PrivateKey key = new PrivateKey("EA6D97F31E4B70663594DD6AFC3E3550AAB5FDD9C44305E8F8F2003023B27FDA");

        MsgCreatePool msg = new MsgCreatePool();
        msg.init(key);

        Message messages = msg.produceMsg(
                "100",
                "de",
                "turing_pool",
                "de"
        );
        JSONObject res = msg.submit(messages, "0.05", "500000", "create pool!");

        try {
            boolean succeed = msg.isTxSucceed(res);
            System.out.println("tx " + (succeed ? "succeed": "failed"));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
