package com.dechain.face;

import com.dechain.msg.coin.BaseMsg;
import com.dechain.msg.pay.BusinessInfo;
import com.dechain.msg.pay.OrderInfo;
import com.dechain.msg.pay.PayBaseInfo;
import com.dechain.msg.red.RedPackInfo;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint256;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PayCenterFace {
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
    public static String approve(String contractFrom, String contractTo, String priKey, BigInteger approveAmount){
        List<Type> params= Arrays.asList(new Address(contractTo),new Uint256(approveAmount));
        return TransactionFace.callContractFunctionOp(priKey,contractFrom,params,"approve",GAS_LIMIT.toBigInteger(),GAS_PRICE.toBigInteger());
    }


    /**
     * 注册成为商家
     * 此方法需要先调用授权
     * @param supportAmount 保证金
     * @param logo 商家图标地址
     * @param name 商家名称
     * @param payContract 支付平台合约
     * @return
     */
    public static String createBusiness(BigInteger supportAmount,String logo,String name,String prikey,String payContract){
        List<Type> params= Arrays.asList(new Uint256(supportAmount),new Utf8String(logo),new Utf8String(name));
        return TransactionFace.callContractFunctionOp(prikey,payContract,params,"createBusiness",GAS_LIMIT.toBigInteger(),GAS_PRICE.toBigInteger());
    }


    /**
     * 商家下单
     * @param amount 下单数量
     * @param oid 订单号（自行生成，需保证唯一）
     * @param payContract 支付平台合约
     * @return
     */
    public static String createOrder(BigInteger amount,String oid,String prikey,String payContract){
        List<Type> params= Arrays.asList(new Utf8String(oid),new Uint256(amount));
        return TransactionFace.callContractFunctionOp(prikey,payContract,params,"createOrder",GAS_LIMIT.toBigInteger(),GAS_PRICE.toBigInteger());
    }


    /**
     * 用户支付
     * 此方法需要先调用授权
     * @param oid 订单号
     * @param payContract 支付平台合约
     * @return
     */
    public static String payOrder(String oid,String prikey,String payContract){
        List<Type> params= Arrays.asList(new Utf8String(oid));
        return TransactionFace.callContractFunctionOp(prikey,payContract,params,"payOrder",GAS_LIMIT.toBigInteger(),GAS_PRICE.toBigInteger());
    }


    /**
     * 商家撤单
     * @param oid 订单号
     * @param payContract 支付平台合约
     * @return
     */
    public static String cancelOrder(String oid,String prikey,String payContract){
        List<Type> params= Arrays.asList(new Utf8String(oid));
        return TransactionFace.callContractFunctionOp(prikey,payContract,params,"cancelOrder",GAS_LIMIT.toBigInteger(),GAS_PRICE.toBigInteger());
    }

    /**
     * 商家提现
     * @param amount 下单数量
     * @param toAddress 收款地址
     * @param payContract 支付平台合约
     * @return
     */
    public static String withdrawFromBalance(BigInteger amount,String toAddress,String prikey,String payContract){
        List<Type> params= Arrays.asList(new Uint256(amount),new Utf8String(toAddress));
        return TransactionFace.callContractFunctionOp(prikey,payContract,params,"withdrawFromBalance",GAS_LIMIT.toBigInteger(),GAS_PRICE.toBigInteger());
    }


    /**
     * 商家注销
     * @param payContract 支付平台合约
     * @return
     */
    public static String quitBusiness(String prikey,String payContract){
        List<Type> params= new ArrayList<>();
        return TransactionFace.callContractFunctionOp(prikey,payContract,params,"quitBusiness",GAS_LIMIT.toBigInteger(),GAS_PRICE.toBigInteger());
    }


    /**
     * 查询商家信息
     */
    public static BusinessInfo findBusiness(String address, String contract){
        BusinessInfo businessInfo=new BusinessInfo();
        businessInfo.setAddress(address);
        List<Type> list=new ArrayList<>();
        list.add(new Address(address));
        List<TypeReference<?>> outputParams=new ArrayList<>();
        outputParams.add(new TypeReference<Address>() {});
        outputParams.add(new TypeReference<Utf8String>() {});
        outputParams.add(new TypeReference<Utf8String>() {});
        outputParams.add(new TypeReference<Uint256>() {});
        outputParams.add(new TypeReference<Uint256>() {});
        outputParams.add(new TypeReference<Uint256>() {});
        outputParams.add(new TypeReference<Uint256>() {});
        outputParams.add(new TypeReference<Uint256>() {});
        outputParams.add(new TypeReference<Uint256>() {});
        outputParams.add(new TypeReference<Bool>() {});


        List<Type>  types=TransactionFace.callContractViewMethod("0x3901952De2f16ad9B8646CF59C337d0b445A81Ca",contract,"findBusiness",list,outputParams);
        if (types!=null&&types.size()==5){
            /**
             0: address: ow 0x3901952De2f16ad9B8646CF59C337d0b445A81Ca
             1: string: icon https://avatar.csdnimg.cn/6/F/5/0_qq_31708101.jpg
             2: string: name 商家1号
             3: uint256: status 0
             4: uint256: balance 80000000000000000000
             5: uint256: count 0
             6: uint256: fee 10
             7: uint256: totalFeeBalance 0
             8: uint256: keepBalance 500000000000000000000
             9: bool: used true
             */
            Address ow= (Address)types.get(0);
            Utf8String icon= (Utf8String)types.get(1);
            Utf8String name= (Utf8String)types.get(2);
            Uint256 status= (Uint256)types.get(3);
            Uint256 balance= (Uint256)types.get(4);
            Uint256 count= (Uint256)types.get(5);
            Uint256 fee= (Uint256)types.get(6);
            Uint256 totalFeeBalance= (Uint256)types.get(7);
            Uint256 keepBalance= (Uint256)types.get(8);

            businessInfo.setIcon(icon.getValue());
            businessInfo.setAddress(ow.getValue());
            businessInfo.setBalance(balance.getValue());
            businessInfo.setName(name.getValue());
            businessInfo.setStatus(status.getValue());
            businessInfo.setCount(count.getValue());
            businessInfo.setFee(fee.getValue());
            businessInfo.setTotalFeeBalance(totalFeeBalance.getValue());
            businessInfo.setKeepBalance(keepBalance.getValue());
            return businessInfo;
        }
        return null;
    }



    /**
     * 查询订单信息
     */
    public static OrderInfo findOrder(String orderId, String contract){
        OrderInfo orderInfo=new OrderInfo();
        orderInfo.setOid(orderId);
        List<Type> list=new ArrayList<>();
        list.add(new Utf8String(orderId));
        List<TypeReference<?>> outputParams=new ArrayList<>();
        outputParams.add(new TypeReference<Utf8String>() {});
        outputParams.add(new TypeReference<Uint256>() {});
        outputParams.add(new TypeReference<Uint256>() {});
        outputParams.add(new TypeReference<Address>() {});
        outputParams.add(new TypeReference<Uint256>() {});
        outputParams.add(new TypeReference<Bool>() {});
        outputParams.add(new TypeReference<Address>() {});


        List<Type>  types=TransactionFace.callContractViewMethod("0x3901952De2f16ad9B8646CF59C337d0b445A81Ca",contract,"findOrder",list,outputParams);
        if (types!=null&&types.size()==5){
            /**
             * 0: string: oid
             * 1: uint256: amount 300000000000000000000
             * 2: uint256: status 1
             * 3: address: payUser 0x3901952De2f16ad9B8646CF59C337d0b445A81Ca
             * 4: uint256: block 128719
             * 5: bool: used true
             * 6: address: business 0x3901952De2f16ad9B8646CF59C337d0b445A81Ca
             */
            Utf8String oid= (Utf8String)types.get(0);
            Uint256 amount= (Uint256)types.get(1);
            Uint256 status= (Uint256)types.get(2);
            Address payUser=(Address)types.get(3);
            Uint256 block= (Uint256)types.get(4);
            Address business=(Address)types.get(6);


            orderInfo.setOid(oid.getValue());
            orderInfo.setAmount(amount.getValue());
            orderInfo.setStatus(status.getValue());
            orderInfo.setPayUser(payUser.getValue());
            orderInfo.setBlock(block.getValue());
            orderInfo.setBusiness(business.getValue());

            return orderInfo;
        }
        return null;
    }



    /**
     * 查询支付基础信息
     */
    public static PayBaseInfo findPayBaseInfo(String contract){
        List<Type> list=new ArrayList<>();
        List<TypeReference<?>> outputParams=new ArrayList<>();
        outputParams.add(new TypeReference<Address>() {});
        outputParams.add(new TypeReference<Bool>() {});
        outputParams.add(new TypeReference<Uint>() {});
        outputParams.add(new TypeReference<Uint>() {});
        outputParams.add(new TypeReference<Uint>() {});
        outputParams.add(new TypeReference<Uint>() {});
        List<Type>  types=TransactionFace.callContractViewMethod("0x3901952De2f16ad9B8646CF59C337d0b445A81Ca",contract,"getPayBaseInfo",list,outputParams);
        if (types!=null&&types.size()==6){
            /**
             *         owner,
             *         openStatus,
             *         total,
             *         totalFee,
             *         baseKeepAmount,
             *         baseFee
             */
            Address owner=(Address)types.get(0);
            Bool openStatus= (Bool)types.get(1);
            Uint total= (Uint)types.get(2);
            Uint totalFee= (Uint)types.get(3);
            Uint baseKeepAmount= (Uint)types.get(4);
            Uint baseFee= (Uint)types.get(5);
            PayBaseInfo payBaseInfo=new PayBaseInfo();
            payBaseInfo.setBaseFee(baseFee.getValue());
            payBaseInfo.setBaseKeepAmount(baseKeepAmount.getValue());
            payBaseInfo.setOpenStatus(openStatus.getValue());
            payBaseInfo.setOwn(owner.getValue());
            payBaseInfo.setTotal(total.getValue());
            payBaseInfo.setTotalFee(totalFee.getValue());
            return payBaseInfo;
        }
        return null;
    }




    /**
     * 查询商家列表
     * @param
     */
    public static  List<BusinessInfo> getBusinessList(String contract){
        List<Type> list=new ArrayList<>();
        List<TypeReference<?>> outputParams=new ArrayList<>();
        outputParams.add(new TypeReference<DynamicArray<Address>>() {});
        outputParams.add(new TypeReference<DynamicArray<Utf8String>>() {});
        outputParams.add(new TypeReference<DynamicArray<Utf8String>>() {});
        outputParams.add(new TypeReference<DynamicArray<Uint256>>() {});
        outputParams.add(new TypeReference<DynamicArray<Uint256>>() {});
        outputParams.add(new TypeReference<DynamicArray<Uint256>>() {});
        outputParams.add(new TypeReference<DynamicArray<Uint256>>() {});
        outputParams.add(new TypeReference<DynamicArray<Uint256>>() {});
        List<Type>  types=TransactionFace.callContractViewMethod("0x3901952De2f16ad9B8646CF59C337d0b445A81Ca",contract,"getAllBusiness",list,outputParams);
        if (types!=null&&types.size()==8){
            /**
             *         address[] memory  _ows,
             *         string[] memory  _icons,
             *         string[] memory  _names,
             *         uint[] memory  _statuss,
             *         uint[] memory  _balances,
             *         uint[] memory  _counts,
             *         uint[] memory  _fees,
             *         uint[] memory  _totalFeeBalances,
             */
            Array ows= (Array)types.get(0);
            Array icons= (Array)types.get(1);
            Array names= (Array)types.get(2);
            Array statuss= (Array)types.get(3);
            Array balances= (Array)types.get(4);
            Array counts= (Array)types.get(5);
            Array fees= (Array)types.get(6);
            Array totalFeeBalances= (Array)types.get(7);

            List<Address> owsList=ows.getValue();
            List<Utf8String> iconsList=icons.getValue();
            List<Utf8String> namesList=names.getValue();
            List<Uint> statussList=statuss.getValue();
            List<Uint> balancesList=balances.getValue();
            List<Uint> countsList=counts.getValue();
            List<Uint> feesList=fees.getValue();
            List<Uint> totalFeeBalancesList=totalFeeBalances.getValue();

            List<String> owsListObj=owsList.stream().map(address -> {
                return address.getValue();
            }).collect(Collectors.toList());
            List<String> iconsListObj=iconsList.stream().map(address -> {
                return address.getValue();
            }).collect(Collectors.toList());
            List<String> namesListObj=namesList.stream().map(address -> {
                return address.getValue();
            }).collect(Collectors.toList());
            List<BigInteger> statussListObj=statussList.stream().map(address -> {
                return address.getValue();
            }).collect(Collectors.toList());
            List<BigInteger> balancesListObj=balancesList.stream().map(address -> {
                return address.getValue();
            }).collect(Collectors.toList());
            List<BigInteger> countsListObj=countsList.stream().map(address -> {
                return address.getValue();
            }).collect(Collectors.toList());
            List<BigInteger> feesListObj=feesList.stream().map(address -> {
                return address.getValue();
            }).collect(Collectors.toList());
            List<BigInteger> totalFeeBalancesListObj=totalFeeBalancesList.stream().map(address -> {
                return address.getValue();
            }).collect(Collectors.toList());

            List<BusinessInfo> businessInfos=new ArrayList<>();
            for(int i=0;i<owsListObj.size();i++){
                BusinessInfo businessInfo=new BusinessInfo();
                businessInfo.setAddress(owsListObj.get(i));
                businessInfo.setTotalFeeBalance(totalFeeBalancesListObj.get(i));
                businessInfo.setFee(feesListObj.get(i));
                businessInfo.setCount(countsListObj.get(i));
                businessInfo.setStatus(statussListObj.get(i));
                businessInfo.setName(namesListObj.get(i));
                businessInfo.setBalance(balancesListObj.get(i));
                businessInfo.setIcon(iconsListObj.get(i));
                businessInfos.add(businessInfo);
            }
            return businessInfos;
        }
        return null;
    }


    /**
     * 启用 /禁用 支付
     * @param payContract 支付平台合约
     * @return
     */
    public static BaseMsg payOnOrClose(String prikey,String payContract){
        List<Type> params= new ArrayList<>();
        return BaseFace.dealMsg(TransactionFace.callContractFunctionOp(prikey,payContract,params,"dealContractStatus",GAS_LIMIT.toBigInteger(),GAS_PRICE.toBigInteger()));
    }


    /**
     * 改变商家状态
     * @param payContract 支付平台合约
     * @return
     */
    public static BaseMsg changeBusiness(String prikey,String payContract,String businessAddress){
        List<Type> params= Arrays.asList(new Address(businessAddress));
        return BaseFace.dealMsg(TransactionFace.callContractFunctionOp(prikey,payContract,params,"changeBusiness",GAS_LIMIT.toBigInteger(),GAS_PRICE.toBigInteger()));
    }
    /**
     * 改变全局最低保证金
     * @param payContract 支付平台合约
     * @return
     */
    public static BaseMsg changeKeepBalance(String prikey,String payContract,BigInteger amount){
        List<Type> params= Arrays.asList(new Uint256(amount));
        return BaseFace.dealMsg(TransactionFace.callContractFunctionOp(prikey,payContract,params,"changeKeepBalance",GAS_LIMIT.toBigInteger(),GAS_PRICE.toBigInteger()));
    }
    /**
     * 改变全局基础费率
     * @param payContract 支付平台合约
     * @return
     */
    public static BaseMsg changeBaseFee(String prikey,String payContract,BigInteger fee){
        List<Type> params= Arrays.asList(new Uint256(fee));
        return BaseFace.dealMsg(TransactionFace.callContractFunctionOp(prikey,payContract,params,"changeBaseFee",GAS_LIMIT.toBigInteger(),GAS_PRICE.toBigInteger()));
    }
    /**
     * 改变商家费率
     * @param payContract 支付平台合约
     * @return
     */
    public static BaseMsg changeBusinessFee(String prikey,String payContract,String business,BigInteger fee){

        List<Type> params= Arrays.asList(new Address(business),new Uint256(fee));
        return BaseFace.dealMsg(TransactionFace.callContractFunctionOp(prikey,payContract,params,"changeBusinessFee",GAS_LIMIT.toBigInteger(),GAS_PRICE.toBigInteger()));
    }

    /**
     * 增加为一个商家
     * @param payContract 支付平台合约
     * @return
     */
    public static BaseMsg addBusiness(String prikey, String payContract, String address, String name, String icon){
        List<Type> params= Arrays.asList(new Utf8String(icon),new Utf8String(name),new Address(address));
        return BaseFace.dealMsg(TransactionFace.callContractFunctionOp(prikey,payContract,params,"addBusiness",GAS_LIMIT.toBigInteger(),GAS_PRICE.toBigInteger()));
    }


    public static void main(String[] args) {

    }





}
