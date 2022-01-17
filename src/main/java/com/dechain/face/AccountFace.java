package com.dechain.face;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dechain.env.EnvBase;
import com.dechain.env.EnvInstance;
import com.dechain.utils.EthConvert;
import com.dechain.utils.crypto.AddressUtil;
import com.dechain.utils.crypto.Crypto;
import org.apache.commons.lang3.StringUtils;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;


/**
 * 账户类接口
 */
public class AccountFace {
    private static final long MOD = 97L;
    private static final long MAX = 999999999L;
    /**
     * 生成助记词、私钥、地址、公钥
     * @return
     */
    public static String createAccount(){
        String mnemonic= Crypto.generateMnemonic();
        String pri=Crypto.generatePrivateKeyFromMnemonic(mnemonic);
        String address=Crypto.generateAddressFromPriv(pri);
        String pub=Crypto.generatePubKeyHexFromPriv(pri);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("mnemonic",mnemonic);
        jsonObject.put("pri",pri);
        jsonObject.put("address",address);
        jsonObject.put("pub",pub);
        return jsonObject.toJSONString();
    }


    /**
     * 导入助记词，生成私钥、地址、公钥
     * @param mnemonic
     * @return
     */
    public static String importByMnemonic(String mnemonic){
        String pri=Crypto.generatePrivateKeyFromMnemonic(mnemonic);
        String address=Crypto.generateAddressFromPriv(pri);
        String pub=Crypto.generatePubKeyHexFromPriv(pri);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("mnemonic",mnemonic);
        jsonObject.put("pri",pri);
        jsonObject.put("address",address);
        jsonObject.put("pub",pub);
        return jsonObject.toJSONString();
    }

    /**
     * 导入私钥，生成地址、公钥
     * @param pri
     * @return
     */
    public static String importByPrivateKey(String pri){
        String address=Crypto.generateAddressFromPriv(pri);
        String pub=Crypto.generatePubKeyHexFromPriv(pri);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("mnemonic","");
        jsonObject.put("pri",pri);
        jsonObject.put("address",address);
        jsonObject.put("pub",pub);
        return jsonObject.toJSONString();
    }

    /**
     * 地址转0x地址
     * @param address
     * @return
     */
    public static String addressToHex(String address){
        return AddressUtil.convertAddressFromBech32ToHex(address);
    }


    /**
     * 0x地址转Bech32地址
     * @param hexAddr
     * @return
     */
    public static String hexToAddress(String hexAddr){
        return AddressUtil.convertAddressFromHexToBech32(hexAddr);
    }


    /**
     * 获取主币余额
     * @param address
     * @return
     */
    public static BigDecimal getMainCoinBalance(String address){
        if (StringUtils.isEmpty(address)){
            System.out.println("address is null");
            return null;
        }
        if (!address.startsWith("0x")){
            address= AccountFace.addressToHex(address);
        }
        Web3j web3j=EnvInstance.getEnv().getWeb3j();
        EthGetBalance getBalance = null;
        try {
            getBalance = web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST).send();
            return Convert.fromWei(getBalance.getBalance().toString(), Convert.Unit.ETHER);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return BigDecimal.ZERO;
    }

    /**
     * 获取代币余额
     * @param address 地址
     * @param contractAddress 合约地址
     * @param len 单位长度
     * @return
     */
    public static BigDecimal getContractCoinBalance(String address,String contractAddress,Integer len){
        if (StringUtils.isEmpty(address)){
            System.out.println("address is null");
            return null;
        }
        if (!address.startsWith("0x")){
            address= AccountFace.addressToHex(address);
        }
        BigInteger balance = BigInteger.ZERO;
        List<Type> params= Arrays.asList(new Address(address));
        List<TypeReference<?>> outputParams=new ArrayList<>();
        outputParams.add(new TypeReference<Uint256>() {});
        List<Type>  types=TransactionFace.callContractViewMethod("0x3901952De2f16ad9B8646CF59C337d0b445A81Ca",contractAddress,"balanceOf",params,outputParams);
        if (types!=null&&types.size()==1){
            /**
             * 0: uint256: amount 50000000000000000000
             */

            Uint256 amount= (Uint256)types.get(0);
            balance=amount.getValue();
        }
        return EthConvert.fromWei(new BigDecimal(balance), EthConvert.Unit.fromLen(len));

    }




    public static void main(String[] args) {
        //System.out.println(AccountFace.importByMnemonic("shy smile throw crawl broken wage like blouse easily office under final long ignore width envelope mosquito mention face slam armor garbage grief laundry"));
        //String a=IbanUtil.calculateCheckDigit("XE7338O073KYGTWWZN0F2WZ0R8PX5ZPPZS");
        //System.out.println(a);
       // String addr="de1kwh0z7kmcyx7kkq53anjs8k2dj46fgc33uc9gt";
       // System.out.println(AccountFace.addressToHex(addr));
        /*EnvInstance.setEnv(new EnvBase("192.168.6.42"));
        System.out.println(AccountFace.createAccount());
        System.out.println(AccountFace.getMainCoinBalance("de1u5rrg73d4hflx6psfp06pyadzy669dykg0lprl"));
        System.out.println(AccountFace.getContractCoinBalance("de1u5rrg73d4hflx6psfp06pyadzy669dykg0lprl","0xfd6656fc53f1d389e23877c59f357e65bd3d6f91",18));
*/

        EnvInstance.setEnv(new EnvBase("39.103.141.174"));
        //System.out.println(AccountFace.getContractCoinBalance("0xb3aef17adbc10deb58148f67281eca6caba4a311","0x6204865bff7ac09743aaf9e97a570a4bd3cffd92",18));
    /*    Long c1=System.currentTimeMillis();

        System.out.println(AccountFace.addressToHex("de1m46cdqk83f95qm877ekhqu62dn88c20vq8rfad"));
        System.out.println(AccountFace.getContractCoinBalance("0x8a7D005647e1d12717ea1C15eE8CC686Fee4c226","0x938758e2801d67c024b28007b1e3eefaa265bc47",18));
        Long c2=System.currentTimeMillis();
        System.out.println(c2-c1);*/
       // System.out.println(AccountFace.getContractCoinBalance("0x2911670c2e5873fc17b7dc6e769d5c53ee1713aa","0x6ffd23b944a2075fcffe2de1d66067092269645e",18));
        System.out.println(AccountFace.getMainCoinBalance("de1rp5spkc9evu4v35w9fqhc5qzrntmnrwn492aqy"));
        //System.out.println(AccountFace.importByMnemonic("off zoo one tiny educate latin input memory produce code jar gospel"));
    }



}
