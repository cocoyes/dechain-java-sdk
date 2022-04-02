package com.dechain.face;

import com.dechain.env.EnvBase;
import com.dechain.env.EnvInstance;
import com.dechain.msg.coin.BaseMsg;
import com.dechain.utils.ContractUtil;
import com.dechain.utils.GoodsSol;
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

public class ExShopFace {

    //注册商品合约
    public static BaseMsg callRegister(String priKey, String master,String coin,BigInteger num,BigInteger price){
        GoodsSol goods=ContractUtil.createGoods(priKey,master,coin,num,price);
        if(goods!=null){
            List<Type> params= Arrays.asList(new Address(goods.getContractAddress()));
            BaseMsg baseMsg=BaseFace.dealMsg(TransactionFace.callContractFunctionOp(priKey,master,params,"callRegister",GAS_LIMIT.toBigInteger(),GAS_PRICE.toBigInteger()));
            if(baseMsg.isSuccess()){
                baseMsg.setData(goods.getContractAddress());
            }
            return baseMsg;
        }else {
            return BaseMsg.buildError("deploy error");
        }
    }

    // 操作订单状态
    public static BaseMsg opOrder(String priKey, String master,String goods,int status){
        List<Type> params= Arrays.asList(new Address(goods),new Uint256(status));
        return BaseFace.dealMsg(TransactionFace.callContractFunctionOp(priKey,master,params,"opOrder",GAS_LIMIT.toBigInteger(),GAS_PRICE.toBigInteger()));
    }

    public static BaseMsg changeNumAndPrice(String priKey, String goods,BigInteger num,BigInteger price){
        List<Type> params= Arrays.asList(new Uint256(num),new Uint256(price));
        return BaseFace.dealMsg(TransactionFace.callContractFunctionOp(priKey,goods,params,"changeNumAndPrice",GAS_LIMIT.toBigInteger(),GAS_PRICE.toBigInteger()));
    }

    //提取锁死订单的保证金
    public static BaseMsg widForceLockOrder(String priKey, String master,String goods,String orderId,String to){
        List<Type> params= Arrays.asList(new Address(goods),new Utf8String(orderId),new Address(to));
        return BaseFace.dealMsg(TransactionFace.callContractFunctionOp(priKey,master,params,"widForceLockOrder",GAS_LIMIT.toBigInteger(),GAS_PRICE.toBigInteger()));
    }

    /**
     *
     * @param priKey
     * @param goods 我的商品合约
     * @param otherGoods 对方商品合约
     * @param myOrderId 我的订单
     * @param otherOrderId 对方订单
     * @param myNum 我需发货的商品数量
     * @param otherNum 对方需发货的商品数量
     * @param myPay 是否是我支付找零
     * @param payAmount 我支付找零的金额
     * @return
     */
    public static BaseMsg createOrder(String priKey,String goods,String otherGoods,String myOrderId,String otherOrderId,int myNum,int otherNum,boolean myPay,BigInteger payAmount){
        List<Type> params= Arrays.asList(new Address(otherGoods),new Utf8String(myOrderId),new Utf8String(otherOrderId),new Uint256(myNum),new Uint256(otherNum),new Bool(myPay),new Uint256(payAmount));
        return BaseFace.dealMsg(TransactionFace.callContractFunctionOp(priKey,goods,params,"createOrder",GAS_LIMIT.toBigInteger(),GAS_PRICE.toBigInteger()));
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

    }


}
