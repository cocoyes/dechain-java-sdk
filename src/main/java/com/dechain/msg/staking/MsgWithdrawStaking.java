package com.dechain.msg.staking;

import com.dechain.env.EnvInstance;
import com.dechain.msg.MsgBase;
import com.dechain.msg.common.Message;
import com.dechain.msg.common.DecCoin;
import com.dechain.utils.Utils;

public class MsgWithdrawStaking  extends MsgBase {
    public MsgWithdrawStaking() {
        setMsgType("dechain/staking/MsgWithdraw");
    }
    public static void main(String[] args) {
        EnvInstance.setEnv("okq");

        MsgWithdrawStaking msg = new MsgWithdrawStaking();
        msg.initMnemonic("puzzle glide follow cruel say burst deliver wild tragic galaxy lumber offer");

        Message messages = msg.produceMsg("de", "10.00000000", "dechain10q0rk5qnyag7wfvvt7rtphlw589m7frsku8qc9");

        msg.submit(messages, "6.00000000", "200000", "");
    }

    public Message produceMsg(String denom, String amountDenom, String delegrator) {

        DecCoin d = new DecCoin();
        d.setDenom(denom);
        d.setAmount(Utils.NewDecString(amountDenom));

        MsgWithdrawStakingValue value = new MsgWithdrawStakingValue();

        System.out.println("this.operAddress:");
        System.out.println(this.operAddress);

        value.setAmount(d);
        value.setDelegatorAddress(delegrator);

        System.out.println("println this.operAdddress");

        Message<MsgWithdrawStakingValue> msg = new Message<>();
        msg.setType(msgType);
        msg.setValue(value);
        return msg;
    }
}
