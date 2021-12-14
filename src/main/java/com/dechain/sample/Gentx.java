package com.dechain.sample;

import com.dechain.msg.MsgBase;
import com.dechain.msg.staking.MsgCreateValidator;
import com.dechain.msg.common.Message;
import com.dechain.msg.common.Signature;
import com.dechain.msg.tx.BroadcastValue;
import com.dechain.msg.tx.UnsignedTx;
import com.dechain.utils.crypto.PrivateKey;

public class Gentx {
    public static void main(String[] args) {
//        String priKey = Crypto.generatePrivateKeyFromMnemonic("race imitate stay curtain puppy suggest spend toe old bridge sunset pride");
        PrivateKey key = new PrivateKey("b4083733cd8379f1249cb9431a074e495a64ae003273d31a7e58356eaad8a0cf");

        MsgCreateValidator msg = new MsgCreateValidator();
        System.out.println(key.getPubKey());
        msg.init(key.getPubKey(),"0","0");

        Message messages = msg.produceMsg(
                "dechainvalconspub1zcjduepqtv2yy90ptjegdm34vfhlq2uw9eu39hjrt98sffj7yghl4s47xv7svt56mk",
                "val0","","","", "10000.00000000");

        try {
            UnsignedTx unsignedTx = msg.getUnsignedTx(messages,"", "200000", "");
            Signature signature = MsgBase.signTx(unsignedTx.toString(), key.getPriKey());
            BroadcastValue signedTx = unsignedTx.sign4gentx(signature);

            System.out.println("======= gentx json =========");
            System.out.println(signedTx.toJson());

        } catch (Exception e) {
            System.out.println("serialize transfer msg failed");
        }
    }
}
