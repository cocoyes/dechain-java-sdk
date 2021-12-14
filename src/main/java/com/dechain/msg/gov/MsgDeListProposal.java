package com.dechain.msg.gov;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.dechain.env.EnvInstance;
import com.dechain.msg.MsgBase;
import com.dechain.msg.common.Message;
import com.dechain.msg.common.Token;
import com.dechain.msg.token.MsgTokenIssue;
import com.dechain.utils.Utils;
import com.dechain.utils.crypto.PrivateKey;

import java.util.ArrayList;
import java.util.List;

public class MsgDeListProposal extends MsgBase {
    public MsgDeListProposal() {
        setMsgType("dechain/gov/MsgSubmitProposal");
    }

    public static void main(String[] args) throws JsonProcessingException {
        EnvInstance.getEnv().setChainID("dechainevm-8");
        EnvInstance.getEnv().setRestServerUrl("http://localhost:8545");

        PrivateKey key = new PrivateKey("EA6D97F31E4B70663594DD6AFC3E3550AAB5FDD9C44305E8F8F2003023B27FDA");
        MsgDeListProposal msg = new MsgDeListProposal();
        msg.init(key);

        Message messages = msg.produceDelistProposalMsg(
                "delete token pair proposal",
                "delete xxx-de",
                "usdk-017",
                EnvInstance.getEnv().GetDenom(),
                "100.00000000"
        );

        msg.submit(messages, "0.05000000", "500000", "OKExChain delete token pair!");
    }

    public Message produceDelistProposalMsg(
            String title,
            String description,
            String baseAsset,
            String quoteAsset,
            String amountDeposit
    ) {

        // proposal
        MsgDeListProposalValue proposal = new MsgDeListProposalValue();
        proposal.setTitle(title);
        proposal.setDescription(description);
        proposal.setProposer(this.address);
        proposal.setBaseAsset(baseAsset);
        proposal.setQuoteAsset(quoteAsset);

        // wrapper
        Content<MsgDeListProposalValue> content = new Content<>();
        content.setType("dechain/dex/DelistProposal");
        content.setValue(proposal);

        // submit
        List<Token> depositList = new ArrayList<>();
        Token deposit = new Token();
        deposit.setDenom(EnvInstance.getEnv().GetDenom());
        deposit.setAmount(Utils.NewDecString(amountDeposit));
        depositList.add(deposit);

        MsgSubmitProposalValue<Content<MsgDeListProposalValue>> value = new MsgSubmitProposalValue<>();
        value.setContent(content);
        value.setInitialDeposit(depositList);
        value.setProposer(this.address);

        Message<MsgSubmitProposalValue<Content<MsgDeListProposalValue>>> msg = new Message<>();
        msg.setType(msgType);
        msg.setValue(value);
        return msg;
    }
}
