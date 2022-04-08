package com.dechain.face;

import com.alibaba.fastjson.JSON;
import com.dechain.env.EnvBase;
import com.dechain.env.EnvInstance;
import com.dechain.msg.coin.BaseMsg;
import com.dechain.msg.coin.PayOrderInfo;
import com.dechain.utils.HttpUtils;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint256;

import java.io.IOException;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

import static com.dechain.msg.coin.BaseMsg.GAS_LIMIT;
import static com.dechain.msg.coin.BaseMsg.GAS_PRICE;

public class RegisterFace {

    //新增应用
    public static BaseMsg registerBusiness(String priKey, String contract,String appId, String name){
        List<Type> params= Arrays.asList(new Utf8String(appId),new Utf8String(name));
        return BaseFace.dealMsg(TransactionFace.callContractFunctionOp(priKey,contract,params,"registerBusiness",GAS_LIMIT.toBigInteger(),GAS_PRICE.toBigInteger()));
    }

    // 修改结算地址
    public static BaseMsg changeWalletAddress(String priKey, String contract,String appId, String address){
        List<Type> params= Arrays.asList(new Utf8String(appId),new Address(address));
        return BaseFace.dealMsg(TransactionFace.callContractFunctionOp(priKey,contract,params,"changeWalletAddress",GAS_LIMIT.toBigInteger(),GAS_PRICE.toBigInteger()));
    }

    //修改应用收款状态
    public static BaseMsg changeBusinessStatus(String priKey, String contract,String appId, int status){
        List<Type> params= Arrays.asList(new Utf8String(appId),new Uint(new BigInteger(status+"")));
        return BaseFace.dealMsg(TransactionFace.callContractFunctionOp(priKey,contract,params,"changeBusinessStatus",GAS_LIMIT.toBigInteger(),GAS_PRICE.toBigInteger()));
    }

    //结算
    public static BaseMsg widBusiness(String priKey, String contract,String appId, List<String> tokens,List<Integer> feeRates){
        List<Address> ls= tokens.stream().map(addr ->{
            return new Address(addr);
        }).collect(Collectors.toList());
        List<Type> params= Arrays.asList(new Utf8String(appId),new DynamicArray(Address.class,ls),new DynamicArray(Uint256.class,feeRates));
        return BaseFace.dealMsg(TransactionFace.callContractFunctionOp(priKey,contract,params,"widBusiness",GAS_LIMIT.toBigInteger(),GAS_PRICE.toBigInteger()));
    }

    /**
     * 支付
     */
    public static BaseMsg payOrder(String priKey, String contract, String appId, String token, BigInteger amount, String oid){
        amount=amount.multiply(BigInteger.TEN.pow(10));
        List<Type> params= Arrays.asList(new Utf8String(appId),new Address(token),new Uint256(amount),new Utf8String(oid));
        return BaseFace.dealMsg(TransactionFace.callContractFunctionOp(priKey,contract,params,"payOrder",GAS_LIMIT.toBigInteger(),GAS_PRICE.toBigInteger()));
    }

    /**
     * 退款
     */
    public static BaseMsg fundsOrder(String priKey, String contract, String appId,String oid){
        List<Type> params= Arrays.asList(new Utf8String(appId),new Utf8String(oid));
        return BaseFace.dealMsg(TransactionFace.callContractFunctionOp(priKey,contract,params,"fundsOrder",GAS_LIMIT.toBigInteger(),GAS_PRICE.toBigInteger()));
    }
    public static BaseMsg fundsOrderAmount(String priKey, String contract, String appId,String oid,BigInteger amount){
        List<Type> params= Arrays.asList(new Utf8String(appId),new Utf8String(oid),new Uint256(amount));
        return BaseFace.dealMsg(TransactionFace.callContractFunctionOp(priKey,contract,params,"fundsOrderAmount",GAS_LIMIT.toBigInteger(),GAS_PRICE.toBigInteger()));
    }


    public static BaseMsg widBusinessAdmin(String priKey, String contract, String appId,BigInteger fee,BigInteger amount,String toAddress,String token){
        List<Type> params= Arrays.asList(new Utf8String(appId),new Address(token),new Uint256(amount),new Address(toAddress),new Uint256(fee));
        return BaseFace.dealMsg(TransactionFace.callContractFunctionOp(priKey,contract,params,"widBusinessAdmin",GAS_LIMIT.toBigInteger(),GAS_PRICE.toBigInteger()));
    }
    public static BaseMsg widAdmin(String priKey, String contract,BigInteger fee,BigInteger amount,String toAddress,String token){
        List<Type> params= Arrays.asList(new Address(token),new Uint256(amount),new Address(toAddress),new Uint256(fee));
        return BaseFace.dealMsg(TransactionFace.callContractFunctionOp(priKey,contract,params,"widAdmin",GAS_LIMIT.toBigInteger(),GAS_PRICE.toBigInteger()));
    }

    /**
     * 查看应用余额
     *
     */
    public static BigInteger getAppBalance(String appId,String contract,String token){
        List<Type> list=new ArrayList<>();
        List<TypeReference<?>> outputParams=new ArrayList<>();
        outputParams.add(new TypeReference<Uint256>() {});
        list.add(new Utf8String(appId));
        list.add(new Address(token));
        List<Type>  types=TransactionFace.callContractViewMethod("0x3901952De2f16ad9B8646CF59C337d0b445A81Ca",contract,"getAppBalance",list,outputParams);
        if (types!=null&&types.size()==1){
            /**
             * 0: uint256: remaining  50000000000000000000
             */
            Uint256 amount= (Uint256)types.get(0);
            return amount.getValue();
        }
        return BigInteger.ZERO;
    }


    public static PayOrderInfo getOrderInfoMap(String appId,String contract,String orderId){
        List<Type> list=new ArrayList<>();
        List<TypeReference<?>> outputParams=new ArrayList<>();
        outputParams.add(new TypeReference<Utf8String>() {});
        outputParams.add(new TypeReference<Utf8String>() {});
        outputParams.add(new TypeReference<Address>() {});
        outputParams.add(new TypeReference<Uint256>() {});
        outputParams.add(new TypeReference<Uint256>() {});
        outputParams.add(new TypeReference<Address>() {});
        outputParams.add(new TypeReference<Bool>() {});
        list.add(new Utf8String(appId));
        list.add(new Utf8String(orderId));
        List<Type>  types=TransactionFace.callContractViewMethod("0x3901952De2f16ad9B8646CF59C337d0b445A81Ca",contract,"orderInfoMap",list,outputParams);
        if (types!=null&&types.size()==7){
            /**
             * 0: uint256: remaining  50000000000000000000
             */
            Utf8String aId= (Utf8String)types.get(0);
            Utf8String oId= (Utf8String)types.get(1);
            Address token= (Address)types.get(2);
            Uint256 amount= (Uint256)types.get(3);
            Uint256 status= (Uint256)types.get(4);
            Address user= (Address)types.get(5);
            Bool used= (Bool)types.get(6);
            PayOrderInfo payOrderInfo=new PayOrderInfo();
            payOrderInfo.setAmount(amount.getValue());
            payOrderInfo.setAppId(aId.getValue());
            payOrderInfo.setoId(oId.getValue());
            payOrderInfo.setStatus(status.getValue());
            payOrderInfo.setUsed(used.getValue());
            payOrderInfo.setToken(token.getValue());
            payOrderInfo.setUser(user.getValue());
            return payOrderInfo;
        }
        return null;
    }


    public static BaseMsg approve(String contractFrom, String contractTo, String priKey, BigInteger approveAmount){
        approveAmount=approveAmount.multiply(BigInteger.TEN.pow(10));
        List<Type> params= Arrays.asList(new Address(contractTo),new Uint256(approveAmount));
        return BaseFace.dealMsg(TransactionFace.callContractFunctionOp(priKey,contractFrom,params,"approve",GAS_LIMIT.toBigInteger(),GAS_PRICE.toBigInteger()));
    }

    /**
     * 支付流程(应用通过app通信时需要调用这个)
     * 2、支付
     * 3、提交hash
     */
    public static BaseMsg payAndPush(String priKey, String contract, String appId, String token, BigInteger amount, String oid){
        BaseMsg baseMsg=payOrder(priKey,contract,appId,token,amount,oid);
        if(baseMsg.isSuccess()){
            pushHash(baseMsg.getHash(),oid);
        }
        return baseMsg;
    }

    private static boolean pushHash(String hash,String oid){
        Map<String,String> map=new HashMap<>();
        map.put("orderId",oid);
        map.put("hash",hash);
        try {
            HttpUtils.sendPostDataByMap("http://"+EnvInstance.getEnv().getIpAddr()+"/api/pay/subHash/:7003",map,"utf-8");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public static void main(String[] args) {
        EnvInstance.setEnv(new EnvBase("8.142.76.237"));
        String priKey="174e203b630cfd560605bd75c167c1f59f5c670d14e31abc70378a8f59bbb7c8";
        String contract="0xfCbBC816B4785B40f072765ebe0d71610eDe7Ef1";
        String appId="61ca7b3893d8ef64c15e9dbd";
        String token="0x054FE3f044bdd56801D51A78e9B069d07c83b657";
        BigInteger amount=new BigInteger("1000").multiply(BigInteger.TEN.pow(18));
        String oid="P1481452573512179715"; //订单号要变更
        //BaseMsg baseMsg=payOrder(priKey,contract,appId,token,amount,oid);

        System.out.println(getOrderInfoMap("61ca7b3893d8ef64c15e9dbd","0x7081353f20E15C54badfFA918c1ac70bd69Df4C2","P1512028229179125762"));
    }


}
