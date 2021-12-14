package com.dechain.msg.staking;

import com.dechain.env.EnvInstance;
import com.dechain.msg.MsgBase;
import com.dechain.msg.common.Message;
import com.dechain.msg.common.Description;

public class MsgEditValidator extends MsgBase {
    public MsgEditValidator() {
        setMsgType("dechain/staking/MsgEditValidator");
    }
    public static void main(String[] args) {
        EnvInstance.setEnv("okq");

        MsgEditValidator msg = new MsgEditValidator();
        msg.setMsgType("dechain/staking/MsgEditValidator");
        msg.initMnemonic("puzzle glide follow cruel say burst deliver wild tragic galaxy lumber offer");

        Message messages = msg.produceMsg("1","1","1","1", "dechainvaloper10q0rk5qnyag7wfvvt7rtphlw589m7frshchly8");

        msg.submit(messages, "6.00000000", "200000", "");
    }

    public Message produceMsg(String details, String identity, String moniker, String website, String operAddress) {

        Description d = new Description();
        d.setDetails(details);
        d.setIdentity(identity);
        d.setMoniker(moniker);
        d.setWebsite(website);

        MsgEditValidatorValue value = new MsgEditValidatorValue();

        value.setAddress(operAddress);

        value.setDescription(d);

        Message<MsgEditValidatorValue> msg = new Message<>();
        msg.setType(msgType);
        msg.setValue(value);
        return msg;
    }
}
