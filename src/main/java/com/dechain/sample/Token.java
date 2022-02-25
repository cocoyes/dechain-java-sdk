package com.dechain.sample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.dechain.env.EnvBase;
import com.dechain.env.EnvInstance;
import com.dechain.msg.common.Message;
import com.dechain.msg.common.TransferUnit;
import com.dechain.msg.token.MsgMultiTransfer;
import com.dechain.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class Token {

    public static void main(String[] args) throws JsonProcessingException {
        EnvBase env = EnvInstance.getEnv();
        env.setChainID("dechainevm-8");
        env.setDenom("de");

        testMultiTransfer();
    }


    static void testMultiTransfer() {
        MsgMultiTransfer msg = new MsgMultiTransfer();
        msg.initMnemonic("giggle sibling fun arrow elevator spoon blood grocery laugh tortoise culture tool");

        List<com.dechain.msg.common.Token> tokens = new ArrayList<>();
        com.dechain.msg.common.Token amount = new com.dechain.msg.common.Token();
        amount.setAmount(Utils.NewDecString("10"));
        amount.setDenom("de");
        tokens.add(amount);
        com.dechain.msg.common.Token amount1 = new com.dechain.msg.common.Token();
        amount1.setAmount(Utils.NewDecString("10"));
        amount1.setDenom("usdk");
        tokens.add(amount1);

        List<TransferUnit> transfers = new ArrayList<>();

        TransferUnit transferUnit = new TransferUnit();
        transferUnit.setCoins(tokens);
        transferUnit.setTo("dechain1twtrl3wvaf9yz6jvt4s726wj6e3cpfxxlgampg");
        transfers.add(transferUnit);

        TransferUnit transferUnit1 = new TransferUnit();
        transferUnit1.setCoins(tokens);
        transferUnit1.setTo("dechain1twtrl3wvaf9yz6jvt4s726wj6e3cpfxxlgampg");
        transfers.add(transferUnit1);

        Message messages = msg.produceMsg(
                transfers
        );

        try {
            msg.submit(messages, "0.03", "2000000", "");
        } catch (Exception e) {
            System.out.println("serialize transfer msg failed"+e.getMessage());
        }
    }
}
