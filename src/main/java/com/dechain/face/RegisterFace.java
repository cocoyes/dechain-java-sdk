package com.dechain.face;

import com.dechain.msg.coin.BaseMsg;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint256;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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




}
