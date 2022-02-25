package com.dechain.msg.staking;

import com.dechain.env.EnvBase;
import com.dechain.env.EnvInstance;
import com.dechain.msg.MsgBase;
import com.dechain.msg.common.Message;
import com.dechain.msg.common.DecCoin;
import com.dechain.utils.Utils;

public class MsgDeposit extends MsgBase {
    public MsgDeposit() {
        setMsgType("/cosmos.staking.v1beta1.MsgDelegate");
    }

    public static void main(String[] args) {
        EnvInstance.setEnv(new EnvBase("192.168.6.38"));

        MsgDeposit msg = new MsgDeposit();
        msg.initPrikey("506f2f4da344307d92069162483f62c43560f40ab7cd99414f01b14539a88108");

        Message messages = msg.produceMsg("aphoton", "10.00000000", "evmosvaloper1u9xjuxhg8qq6dudghpjmjxy2q2zalfgu4uqr26");

        msg.submit(messages, "6.00000000", "200000", "");
    }

    public Message produceMsg(String denom, String amountDenom, String delegrator) {

        DecCoin d = new DecCoin();
        d.setDenom(denom);
        d.setAmount(Utils.NewDecString(amountDenom));

        MsgDepositValue value = new MsgDepositValue();

        System.out.println("this.operAddress:");
        System.out.println(this.operAddress);

        value.setAmount(d);
        value.setDelegatorAddress(delegrator);

        System.out.println("println this.operAdddress");

        Message<MsgDepositValue> msg = new Message<>();
        msg.setType(msgType);
        msg.setValue(value);
        return msg;
    }

}
