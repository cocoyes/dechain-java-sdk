package com.dechain.sample;

import com.dechain.env.EnvInstance;
import com.dechain.utils.crypto.PrivateKey;
import com.dechain.msg.MsgBase;
import com.dechain.msg.token.MsgSend;
import com.dechain.msg.tx.BroadcastTx;
import com.dechain.msg.common.Message;
import com.dechain.msg.tx.UnsignedTx;
import com.dechain.msg.common.Signature;;

public class ColdSign {

    public static void main(String[] args) {
        PrivateKey key = new PrivateKey("8145bfb1d3acc216c54490952c994d5e3bce09dd65ae73d0c79f892284f721e7");
//        PrivateKey key = new PrivateKey("puzzle glide follow cruel say burst deliver wild tragic galaxy lumber offer");

        MsgSend msg = new MsgSend();
        msg.init(key.getPubKey());
//        msg.init(key.getAddress(), "0", "1", key.getPubKey());

        // or init by account number and sequence number
//        msg.init(key.getPubKey(), "0", "10");

        Message messages = msg.produceSendMsg(
                EnvInstance.getEnv().GetDenom(),
                "6.00000000",
                "dechain1q6ls3h64gkxq0r73u2eqwwr7d5mp583f2ats92");

        try {
            UnsignedTx unsignedTx = msg.getUnsignedTx(messages, "0.01000000", "200000", "dechain transfer!");

            Signature signature = MsgBase.signTx(unsignedTx.toString(), key.getPriKey());

            BroadcastTx signedTx = UnsignedTx.genBroadcastTx(unsignedTx.toString(), signature);
            System.out.println(signedTx.toJson());


            MsgBase.broadcast(signedTx.toJson(), EnvInstance.getEnv().GetRestServerUrl());

        } catch (Exception e) {
            System.out.println("serialize transfer msg failed");
        }
    }
}
