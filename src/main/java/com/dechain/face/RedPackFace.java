package com.dechain.face;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dechain.env.EnvBase;
import com.dechain.env.EnvInstance;
import com.dechain.msg.red.RedContractInfo;
import com.dechain.msg.red.RedPackInfo;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.utils.Numeric;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RedPackFace {
    //如果红包数为100个，实际需要花费大于255万gas,抢红包只需要小于15万即可，按实际消耗决定
    //以下单次最多花费手续费0.00000001
    public static final BigDecimal GAS_LIMIT=new BigDecimal(5000000);
    public static final BigDecimal GAS_PRICE=new BigDecimal(2000);

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
        EnvInstance.setEnv(new EnvBase("123.100.236.38"));
        System.out.println(RedPackFace.needFee().toPlainString());
        if (!true){
            test2();
            return;
        }
        //
        System.out.println("查询某个红包的详情:");
        System.out.println(JSON.toJSONString(RedPackFace.getTokenInfo("0x0870349fa0e745e3d916a243105a43c56ba59bde")));
       // System.out.println(JSON.toJSONString(getSendPackInfo("163660258133","0xb529ee2365e3821854be74a33e0ab8a67fc20403")));

      //  RedPackFace.withdrawBalance("0xb529ee2365e3821854be74a33e0ab8a67fc20403","24729b3ca63396f9c0a849de7d6ae0ee86c37646c711378b4319b75452793c12","163660258133");

        /**
         * 模拟与合约交互
         */
        //代币合约
        String coinContract="0x6ffd23b944a2075fcffe2de1d66067092269645e";
        // 红包合约
        String redContract="0x1d4ceeaee0d283c31a5b394ff46630ac7f378f04";

        //含有代币的私钥
        String pri="e7e7f1b09d27caaf3c0f621ec3f1d46610427c5b71f3b63feee6c6cd8ca6c8f0";
        String myadd="0x489EAD38992b4d1127C9F697B8F315AD2011506e";
        System.out.println("代币余额:"+AccountFace.getContractCoinBalance(myadd,coinContract,18));
        // no1 向代币合约申请授权,授权1000个，这里假设代币是18位，其他位数直接改
        BigInteger ba=RedPackFace.getApproveRemainBalance(redContract,myadd,coinContract);
        System.out.println("ba="+ba);
        RedPackFace.approve(coinContract,redContract,pri,BigInteger.valueOf(1000).multiply(BigInteger.TEN.pow(18)));
        try {
            System.out.println("休眠5秒，等待授权成功,授权是需要手续费的");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String redPackId="100000019";
        RedPackFace.createRedPack(redContract,pri,BigInteger.valueOf(1).multiply(BigInteger.TEN.pow(18)),5,redPackId);
        try {
            System.out.println("休眠5秒再查询，等待发布成功");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(JSON.toJSONString(getSendPackInfo(redPackId,redContract)));

        //自己去抢
        RedPackFace.huntingRedPack(redContract,pri,redPackId);
        try {
            System.out.println("休眠5秒再查询，再看看红包详情");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(JSON.toJSONString(getSendPackInfo(redPackId,redContract)));
    }


    public static void test2(){
        RedPackFace.getApproveRemainBalance("0x1cf21b727baa7782b07aa08b6870c38b19659a9d","0xdd758682c78A4B406cfEf66D70734A6CcE7c29eC","0x6ffd23b944a2075fcffe2de1d66067092269645e");
    }

}
