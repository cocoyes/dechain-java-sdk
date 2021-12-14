package com.dechain.sample;

import com.dechain.utils.crypto.AddressConvertUtil;
import com.dechain.utils.crypto.PrivateKey;

/**
 * @author shaoyun.zhan
 * @date 2021/3/31
 * <p>
 * 描述：
 */
public class AddressConvertUtilSample {
    public static void main(String[] args) {
        test();
        //"eth_address": "0x64fAB0187AF0BCfF8499079161d8a0D68Ee8827a"
        //"privateKey": "2843de7dec93946f1022ec9355678fdec3dc49d3140d2314b452a3a4afe78191"
      /*  System.out.println(new PrivateKey("2843de7dec93946f1022ec9355678fdec3dc49d3140d2314b452a3a4afe78191").getAddress());
        System.out.println(AddressConvertUtil.convertFromBech32ToHex("dechain1vnatqxr67z70lpyeq7gkrk9q668w3qn6sufu56").equals("0x64fAB0187AF0BCfF8499079161d8a0D68Ee8827a"));
        System.out.println(AddressConvertUtil.convertFromBech32ToHex("ex1vnatqxr67z70lpyeq7gkrk9q668w3qn6hhzuhk").equals("0x64fAB0187AF0BCfF8499079161d8a0D68Ee8827a"));
        System.out.println(AddressConvertUtil.convertFromHexToOkexchainBech32("0x64fAB0187AF0BCfF8499079161d8a0D68Ee8827a").equals("dechain1vnatqxr67z70lpyeq7gkrk9q668w3qn6sufu56"));
        System.out.println(AddressConvertUtil.convertFromHexToExBech32("0x64fAB0187AF0BCfF8499079161d8a0D68Ee8827a").equals("ex1vnatqxr67z70lpyeq7gkrk9q668w3qn6hhzuhk"));
        System.out.println(AddressConvertUtil.convertFromExBech32ToOkexchainBech32("ex1vnatqxr67z70lpyeq7gkrk9q668w3qn6hhzuhk").equals("dechain1vnatqxr67z70lpyeq7gkrk9q668w3qn6sufu56"));
        System.out.println(AddressConvertUtil.convertFromOkexchainBech32ToExBech32("dechain1vnatqxr67z70lpyeq7gkrk9q668w3qn6sufu56").equals("ex1vnatqxr67z70lpyeq7gkrk9q668w3qn6hhzuhk"));
        System.out.println(AddressConvertUtil.convertFromOkexchainValToExVal("dechainvaloper18au05qx485u2qcw2gvqsrfh29evq77lm45mf4h").equals("exvaloper18au05qx485u2qcw2gvqsrfh29evq77lm9u2jwr"));
*/
    }


    /*
    *
    *   true club burden rigid extra again acid achieve claim hospital blade squeeze
        6153F03C1D0956C8A16BCC1715FD0DF56B340594D87C3EDE526CC46BEE33ED51
        ex1hnjd0pexu538mlwzceg2z3c49sglzyza54xjtt
        0xBcE4d78726e5227DfDc2c650a147152C11F1105D
    * */
    public static void test(){
        String addr=new PrivateKey("A784609B8BF6236A612826C84C3BCAE8F85F58462A406BA71125A9DAD897144D").getAddress();
        System.out.println("addr="+addr);
        System.out.println(AddressConvertUtil.convertFromBech32ToHex(addr));

    }
}
