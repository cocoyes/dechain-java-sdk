package com.dechain.sample;

import com.dechain.utils.crypto.Crypto;

public class AccountGenerate {
    public static void main(String[] args) {
        String mnemonic=Crypto.generateMnemonic();
        String pri=Crypto.generatePrivateKeyFromMnemonic(mnemonic);
        System.out.println("pri="+pri);
        String address=Crypto.generateAddressFromPriv(pri);
        String pub=Crypto.generatePubKeyHexFromPriv(pri);
        System.out.println("addr="+address);

        System.out.println("================================step2================================");
        //wild hub syrup genius valid claim rubber scan discover worth shift hope tourist forward old what purse iron soda journey long velvet okay snow
        String pri2=Crypto.generatePrivateKeyFromMnemonic("wild hub syrup genius valid claim rubber scan discover worth shift hope tourist forward old what purse iron soda journey long velvet okay snow");
        System.out.println("pri2="+pri2);
        String address2=Crypto.generateAddressFromPriv(pri);
        System.out.println("address2="+address2);
    }
}
