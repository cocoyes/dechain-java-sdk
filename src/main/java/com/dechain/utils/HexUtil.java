package com.dechain.utils;

import com.dechain.face.AccountFace;

public class HexUtil {

    public static String toHexAddr(String address){
        if(address.startsWith("0x")){
            return address;
        }else {
            return AccountFace.addressToHex(address);
        }
    }

    public static String hexToAddr(String address){
        if(address.startsWith("0x")){
            return AccountFace.hexToAddress(address);
        }else {
            return address;
        }
    }
}
