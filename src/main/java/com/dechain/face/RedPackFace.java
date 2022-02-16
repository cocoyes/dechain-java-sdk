package com.dechain.face;

import com.alibaba.fastjson.JSON;
import com.dechain.env.EnvBase;
import com.dechain.env.EnvInstance;
import com.dechain.msg.coin.BaseMsg;
import com.dechain.msg.coin.TokenInfo;
import com.dechain.msg.red.RedContractInfo;
import com.dechain.msg.red.RedPackInfo;
import com.dechain.utils.RedpackSol;
import com.dechain.utils.crypto.Crypto;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.dechain.msg.coin.BaseMsg.GAS_LIMIT;
import static com.dechain.msg.coin.BaseMsg.GAS_PRICE;

public class RedPackFace {


    /**
     * 获取每次操作至少需要的手续费
     * 如果是发红包，则需要计算*2，授权也需要一次手续费
     * @return
     */
    public static BigDecimal needFee(){
        return GAS_LIMIT.multiply(GAS_PRICE).divide((BigDecimal.TEN.pow(18)),9,BigDecimal.ROUND_DOWN);
    }

    /**
     * 申请授权
     * @param contractFrom 将这个合约的资产
     * @param contractTo    授权给这个合约操作
     * @param priKey
     * @param approveAmount 授权它操作的数量，如果没有这么多余额也可以成功 注意，这个根据合约单位长度换算好再传
     * @return 返回hash,需要进行查询才知道结果，建议每隔一秒调用一次查询交易状态
     */
    public static String approve(String contractFrom,String contractTo,String priKey,BigInteger approveAmount){
        List<Type> params= Arrays.asList(new Address(contractTo),new Uint256(approveAmount));
        return TransactionFace.callContractFunctionOp(priKey,contractFrom,params,"approve",GAS_LIMIT.toBigInteger(),GAS_PRICE.toBigInteger());
    }


    public static BaseMsg approveSync(String contractFrom,String contractTo,String priKey,BigInteger approveAmount){
        List<Type> params= Arrays.asList(new Address(contractTo),new Uint256(approveAmount));
        return BaseFace.dealMsg(TransactionFace.callContractFunctionOp(priKey,contractFrom,params,"approve",GAS_LIMIT.toBigInteger(),GAS_PRICE.toBigInteger()));
    }


    /**
     * 创建红包
     * @param sendTotal 发送总量     注意，这个根据合约单位长度换算好再传
     * @param count    红包个数
     * @param redPackId    红包id，请保持唯一，重复将发起失败
     * 目前版本仅支持随机红包
     * 查询交易详情判断是否发成功
     */
    public static String createRedPack(String contract,String priKey,BigInteger sendTotal,int count,String redPackId){
        List<Type> params= Arrays.asList(new Uint256(BigInteger.valueOf(count)),new Uint256(sendTotal),new Utf8String(redPackId));
        return TransactionFace.callContractFunctionOp(priKey,contract,params,"toll",GAS_LIMIT.toBigInteger(),GAS_PRICE.toBigInteger());
    }


    /**
     * 抢红包
     * @param contract
     * @param priKey
     * @param redPackId 红包id
     *  查询交易详情判断是否成功
     * @return
     */
    public static String huntingRedPack(String contract,String priKey,String redPackId){
        List<Type> params= Arrays.asList(new Utf8String(redPackId));
        return TransactionFace.callContractFunctionOp(priKey,contract,params,"hunting",GAS_LIMIT.toBigInteger(),GAS_PRICE.toBigInteger());
    }


    /**
     * 提现
     * @param contract
     * @param priKey
     * @param redPackId
     * @return
     */
    public static String withdrawBalance(String contract,String priKey,String redPackId){
        List<Type> params= Arrays.asList(new Utf8String(redPackId));
        return TransactionFace.callContractFunctionOp(priKey,contract,params,"withdrawBalance",GAS_LIMIT.toBigInteger(),GAS_PRICE.toBigInteger());
    }




    /**
     * 查询红包详情
     * @param rid 红包id
     */
    public static RedPackInfo getSendPackInfo(String rid,String contract){
        RedPackInfo redPackInfo=new RedPackInfo();
        redPackInfo.setRid(rid);
        List<Type> list=new ArrayList<>();
        List<TypeReference<?>> outputParams=new ArrayList<>();
        outputParams.add(new TypeReference<Uint256>() {});
        outputParams.add(new TypeReference<Uint256>() {});
        outputParams.add(new TypeReference<Uint256>() {});
        outputParams.add(new TypeReference<DynamicArray<Address>>() {});
        outputParams.add(new TypeReference<DynamicArray<Uint256>>() {});
        list.add(new Utf8String(rid));
        List<Type>  types=TransactionFace.callContractViewMethod("0x3901952De2f16ad9B8646CF59C337d0b445A81Ca",contract,"getPackInfo",list,outputParams);
        if (types!=null&&types.size()==5){
            /**
             * 0: uint256: amount 50000000000000000000
             * 1: uint256: amount 50000000000000000000
             * 2: uint256: balance 41248506822956664767
             * 3: uint256: count 8
             * 4: address[]: hunterInfos 0x3901952De2f16ad9B8646CF59C337d0b445A81Ca
             * 5: uint256[]: pickAmounts 8751493177043335233
             */

            Uint256 amount= (Uint256)types.get(0);
            Uint256 balance= (Uint256)types.get(1);
            Uint256 count= (Uint256)types.get(2);
            Array hunterInfos= (Array)types.get(3);
            Array pickAmounts= (Array)types.get(4);
            redPackInfo.setAmount(amount.getValue());
            redPackInfo.setBalance(balance.getValue());
            redPackInfo.setCount(count.getValue());
            List<Address> ads=hunterInfos.getValue();
            List<Uint256> amt=pickAmounts.getValue();
            List<String> addressList=ads.stream().map(address -> {
                return address.getValue();
            }).collect(Collectors.toList());
            List<BigInteger> amountList=amt.stream().map(uint256 -> {
                return uint256.getValue();
            }).collect(Collectors.toList());
            redPackInfo.setAddress(addressList);
            redPackInfo.setPickAmount(amountList);
            return redPackInfo;
        }
        return null;
    }

    /**
     * 查询授权可用额度
     * @param spender 授权的花费方，指给哪个合约去操作
     * @param myAddr 我的地址
     * @param contractAddr 授权的token合约地址
     */
    public static BigInteger getApproveRemainBalance(String spender,String myAddr,String contractAddr){

        List<Type> list=new ArrayList<>();
        List<TypeReference<?>> outputParams=new ArrayList<>();
        outputParams.add(new TypeReference<Uint256>() {});

        list.add(new Address(myAddr));
        list.add(new Address(spender));
        List<Type>  types=TransactionFace.callContractViewMethod("0x3901952De2f16ad9B8646CF59C337d0b445A81Ca",contractAddr,"allowance",list,outputParams);
        if (types!=null&&types.size()==1){
            /**
             * 0: uint256: remaining  50000000000000000000
             */
            Uint256 amount= (Uint256)types.get(0);

            return amount.getValue();
        }
        return BigInteger.ZERO;
    }



    /**
     * 获取红包通证信息
     */
    public static RedContractInfo getTokenInfo(String redContract){
        List<Type> list=new ArrayList<>();
        List<TypeReference<?>> outputParams=new ArrayList<>();
        outputParams.add(new TypeReference<Uint256>() {});
        outputParams.add(new TypeReference<Address>() {});
        outputParams.add(new TypeReference<Utf8String>() {});
        outputParams.add(new TypeReference<Utf8String>() {});
        List<Type>  types=TransactionFace.callContractViewMethod("0x3901952De2f16ad9B8646CF59C337d0b445A81Ca",redContract,"getTokenInfo",list,outputParams);
        if (types!=null&&types.size()==4){
            /**
             * 0: uint256: amount 50000000000000000000
             */

            Uint256 len= (Uint256)types.get(0);
            Address address= (Address)types.get(1);
            Utf8String name= (Utf8String)types.get(2);
            Utf8String symbol= (Utf8String)types.get(3);
            RedContractInfo redContractInfo=new RedContractInfo();
            redContractInfo.setLen(len.getValue());
            redContractInfo.setContractAddress(address.getValue());
            redContractInfo.setContractName(name.getValue());
            redContractInfo.setContractSymbol(symbol.getValue());
            return redContractInfo;
        }
        return null;
    }



    public static void main(String[] args) {
        EnvInstance.setEnv(new EnvBase("39.103.141.174"));
        if(true){
            widthdraw();
            return;
        }
        Web3j web3j=EnvInstance.getEnv().getWeb3j();
        String pri=Crypto.generatePrivateKeyFromMnemonic("dignity place clip make relief dice lumber win copper profit voice render");
        List<TokenInfo> list=CoinFace.getAllToken("0xAc15653c39b351cAFf64eC691bA56Bf7CEE18Fe5");
        String address=Crypto.generateAddressFromPriv(pri);
        String hex=AccountFace.addressToHex(address);
        list.forEach(tokenInfo -> {
            if(tokenInfo.getSymbol().equalsIgnoreCase("BSS4")){
                String n=CoinFace.getName(tokenInfo.getRed());
                String s=CoinFace.getSymbol(tokenInfo.getRed());


                String name=CoinFace.getName(tokenInfo.getRed());
                BigDecimal ba=AccountFace.getContractCoinBalance(hex,tokenInfo.getToken(),18);
                System.out.println("name="+name+",ba="+ba);
                String approveHash=RedPackFace.approve(tokenInfo.getToken(),tokenInfo.getRed(),pri,new BigInteger("1000").multiply(BigInteger.TEN.pow(18)));
                BaseMsg msgApprove=BaseFace.dealMsg(approveHash);
                System.out.println(JSON.toJSONString(msgApprove));
                System.out.println("授权完成");
                String hash=RedPackFace.createRedPack(tokenInfo.getRed(),pri,new BigInteger("2000000000000000000"),2,"11555558");
                BaseMsg msg=BaseFace.dealMsg(hash);
                System.out.println(JSON.toJSONString(msg));
            }
        });
    }


    public static void test2(){
        RedPackFace.getApproveRemainBalance("0x1cf21b727baa7782b07aa08b6870c38b19659a9d","0xdd758682c78A4B406cfEf66D70734A6CcE7c29eC","0x6ffd23b944a2075fcffe2de1d66067092269645e");
    }


    public static void widthdraw(){
        //0xa2f0a40c9551e765e96e26cc2dbbb1742a02a639
        //8e5f25187fc51c98aa37bb87d216b742bc3594ee2fb24d40b7187e26fb24e4b7
        //164449636977

        System.out.println(JSON.toJSONString(getSendPackInfo("164449636977","0xa2f0a40c9551e765e96e26cc2dbbb1742a02a639")));
        System.out.println(JSON.toJSONString(BaseFace.dealMsg(withdrawBalance("0xa2f0a40c9551e765e96e26cc2dbbb1742a02a639","8e5f25187fc51c98aa37bb87d216b742bc3594ee2fb24d40b7187e26fb24e4b7","164449636977"))));
    }

}
