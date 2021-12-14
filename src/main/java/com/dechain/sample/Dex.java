package com.dechain.sample;
import com.dechain.env.EnvInstance;
import com.dechain.msg.dex.*;
import com.dechain.utils.crypto.PrivateKey;
import com.dechain.msg.*;
import com.dechain.msg.tx.BroadcastTx;
import com.dechain.msg.common.Message;
import com.dechain.msg.tx.UnsignedTx;
import com.dechain.msg.common.Signature;


public class Dex {
    public static void main(String[] args) {
        EnvInstance.getEnv().setRestServerUrl("http://localhost:8545");
        EnvInstance.getEnv().setChainID("dechainevm-8");
        EnvInstance.getEnv().setDenom("de");

//        testMsgCreateOperator();
//        testMsgUpdateOperator();
//        testMsgList();
//        testMsgTransferTokenPairOwnership();
//        testMsgConfirmTokenPairOwnership();
        testMsgWithdrawDeposit();

    }

    static void testMsgCreateOperator(){
        PrivateKey key = new PrivateKey("EA6D97F31E4B70663594DD6AFC3E3550AAB5FDD9C44305E8F8F2003023B27FDA");

        MsgCreateOperator msg = new MsgCreateOperator();
        msg.init(key.getPubKey());

        Message messages = msg.produceCreateOperatorMsg(
                key.getAddress(),
                "https://bob.okg/operator.json");

        try {
            UnsignedTx unsignedTx = msg.getUnsignedTx(messages, "0.02000000", "200000", "dechain dex create operator!");
            Signature signature = MsgBase.signTx(unsignedTx.toString(), key.getPriKey());

            BroadcastTx signedTx = unsignedTx.signed(signature);

            MsgBase.broadcast(signedTx.toJson(), EnvInstance.getEnv().GetRestServerUrl());

        } catch (Exception e) {
            System.out.println("serialize transfer msg failed");
        }
    }

    static void testMsgUpdateOperator(){
        PrivateKey key = new PrivateKey("17157D973569415C616E70BE2537DFB9F48BAD5C7FF088A5FCDF193DD3E450E3");

        MsgUpdateOperator msg = new MsgUpdateOperator();
        msg.init(key.getPubKey());

        Message messages = msg.produceUpdateOperatorMsg(
                "dechain1ntvyep3suq5z7789g7d5dejwzameu08m6gh7yl",
                "https://captain.okg/operator111.json");

        try {
            UnsignedTx unsignedTx = msg.getUnsignedTx(messages,"0.01000000", "200000", "dechain dex create operator!");

            Signature signature = MsgBase.signTx(unsignedTx.toString(), key.getPriKey());

            BroadcastTx signedTx = unsignedTx.signed(signature);

            MsgBase.broadcast(signedTx.toJson(), EnvInstance.getEnv().GetRestServerUrl());

        } catch (Exception e) {
            System.out.println("serialize transfer msg failed");
        }
    }

    static void testMsgList(){
        PrivateKey key = new PrivateKey("EA6D97F31E4B70663594DD6AFC3E3550AAB5FDD9C44305E8F8F2003023B27FDA");

        MsgList msg = new MsgList();
        msg.init(key.getPubKey());

        Message messages = msg.produceListMsg(
                "usdk-5f7",
                EnvInstance.getEnv().GetDenom(),
                "1");

        try {
            UnsignedTx unsignedTx = msg.getUnsignedTx(messages,"0.02000000", "200000", "dechain dex list!");

            Signature signature = MsgBase.signTx(unsignedTx.toString(), key.getPriKey());

            BroadcastTx signedTx = unsignedTx.signed(signature);

            MsgBase.broadcast(signedTx.toJson(), EnvInstance.getEnv().GetRestServerUrl());

        } catch (Exception e) {
            System.out.println("serialize transfer msg failed");
        }
    }

    static void testMsgTransferTokenPairOwnership(){
        PrivateKey key = new PrivateKey("17157D973569415C616E70BE2537DFB9F48BAD5C7FF088A5FCDF193DD3E450E3");

        MsgTransferTokenPairOwnership msg = new MsgTransferTokenPairOwnership();
        msg.init(key.getPubKey());

        Message messages = msg.produceTransferTokenPairOwnershipMsg(
                "dechain1twtrl3wvaf9yz6jvt4s726wj6e3cpfxxlgampg",
                "dechain1ntvyep3suq5z7789g7d5dejwzameu08m6gh7yl",
                "eos-3bd_de"
        );

        try {
            UnsignedTx unsignedTx = msg.getUnsignedTx(messages,"0.01000000", "200000", "dechain transfer token pair ownership!");

            Signature signature = MsgBase.signTx(unsignedTx.toString(), key.getPriKey());

            BroadcastTx signedTx = unsignedTx.signed(signature);

            MsgBase.broadcast(signedTx.toJson(), EnvInstance.getEnv().GetRestServerUrl());

        } catch (Exception e) {
            System.out.println("serialize transfer msg failed");
        }
    }

    static void testMsgConfirmTokenPairOwnership(){
        PrivateKey key = new PrivateKey("EA6D97F31E4B70663594DD6AFC3E3550AAB5FDD9C44305E8F8F2003023B27FDA");

        MsgConfirmTokenPairOwnership msg = new MsgConfirmTokenPairOwnership();
        msg.init(key.getPubKey());

        Message messages = msg.produceConfirmTokenPairOwnershipMsg(
                "dechain1ntvyep3suq5z7789g7d5dejwzameu08m6gh7yl",
                "eos-3bd_de"
        );

        try {
            UnsignedTx unsignedTx = msg.getUnsignedTx(messages,"0.01000000", "200000", "dechain confirm token pair ownership!");

            Signature signature = MsgBase.signTx(unsignedTx.toString(), key.getPriKey());

            BroadcastTx signedTx = unsignedTx.signed(signature);

            MsgBase.broadcast(signedTx.toJson(), EnvInstance.getEnv().GetRestServerUrl());

        } catch (Exception e) {
            System.out.println("serialize transfer msg failed");
        }
    }

    static void testMsgWithdrawDeposit(){
        PrivateKey key = new PrivateKey("3040196C06C630C1E30D6D347B097C9EA64ADA24FB94823B6C755194F3A00761");

        MsgWithdrawDeposit msg = new MsgWithdrawDeposit();
        msg.init(key.getPubKey());

        Message messages = msg.produce(
                "de",
                "1000",
                "eos-c38_de"
        );

        try {
            UnsignedTx unsignedTx = msg.getUnsignedTx(messages,"0.01000000", "200000", "withdraw deposit!");

            Signature signature = MsgBase.signTx(unsignedTx.toString(), key.getPriKey());

            BroadcastTx signedTx = unsignedTx.signed(signature);

            MsgBase.broadcast(signedTx.toJson(), EnvInstance.getEnv().GetRestServerUrl());

        } catch (Exception e) {
            System.out.println("serialize transfer msg failed");
        }
    }
}
