package com.dechain.msg.gov;

import com.dechain.msg.MsgBase;
import com.dechain.msg.common.Message;

public class MsgVote extends MsgBase {

    public MsgVote() {
        setMsgType("dechain/gov/MsgVote");
    }

    public static void main(String[] args) {
        MsgVote msg = new MsgVote();

        msg.initMnemonic("shy smile throw crawl broken wage like blouse easily office under final long ignore width envelope mosquito mention face slam armor garbage grief laundry");

        Message messages = msg.produceVoteMsg(
                "2",
                "Yes");

        msg.submit(messages, "0.01000000", "200000", "dechain gov vot!");
    }

    public Message produceVoteMsg(String ProposalID, String option) {

        MsgVoteValue value = new MsgVoteValue();
        value.setProposalID(ProposalID);
        value.setVoters(this.address);
        value.setOption(option);

        Message<MsgVoteValue> msg = new Message<>();
        msg.setType(msgType);
        msg.setValue(value);
        return msg;
    }

}
