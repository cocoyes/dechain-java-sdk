package com.dechain.msg.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dechain.env.EnvInstance;
import com.dechain.msg.MsgBase;
import com.dechain.msg.common.Message;
import com.dechain.msg.tx.UnsignedTx;
import com.dechain.utils.crypto.PrivateKey;

import java.util.ArrayList;

public class MsgCancelOrders extends MsgBase {

    public MsgCancelOrders() {
        setMsgType("dechain/order/MsgCancel");
    }

    public Message produceMsg(String[] orderIDs) {

        MsgCancelOrdersValue value = new MsgCancelOrdersValue();
        value.setSender(this.address);
        value.setOrderIDs(orderIDs);

        Message<MsgCancelOrdersValue> msg = new Message<>();
        msg.setType(msgType);
        msg.setValue(value);
        return msg;
    }

    public static void main(String[] args) throws Exception {
        EnvInstance.getEnv().setChainID("dechainevm-8");
        EnvInstance.getEnv().setRestServerUrl("http://localhost:8545");

        PrivateKey key = new PrivateKey("EA6D97F31E4B70663594DD6AFC3E3550AAB5FDD9C44305E8F8F2003023B27FDA");
        MsgCancelOrders msg = new MsgCancelOrders();
        msg.init(key);

        ArrayList<String> orderIDs = new ArrayList<>();
        orderIDs.add("ID0000001373-1");
        String[] ids = new String[orderIDs.size()];

        Message messages = msg.produceMsg(orderIDs.toArray(ids));

        JSONObject result = msg.submit(messages, "0.02000000", "200000", "");
        boolean succeed = msg.isTxSucceed(result);
        System.out.println("cancel orders: " + (succeed ? "succeed" : "failed"));
    }
}
