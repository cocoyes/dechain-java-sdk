package com.dechain.face;

import com.dechain.env.EnvBase;
import com.dechain.env.EnvInstance;
import com.dechain.msg.coin.BaseMsg;
import com.dechain.msg.coin.RegisterTokenDto;
import com.dechain.msg.coin.TokenInfo;
import com.dechain.msg.red.RedPackInfo;
import com.dechain.utils.ContractUtil;
import com.dechain.utils.PayCenter_sol_PayCenter;
import com.dechain.utils.Redpack_sol_Redpack;
import com.dechain.utils.StandContract_sol_PubToken;
import com.dechain.utils.crypto.Crypto;
import org.apache.commons.lang3.StringUtils;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static com.dechain.face.PayCenterFace.GAS_LIMIT;
import static com.dechain.face.PayCenterFace.GAS_PRICE;

public class CoinFace {

    /**
     * 获取合约精度
     */
    public static BigInteger getDecimal(String contract){
        List<Type> list=new ArrayList<>();
        List<TypeReference<?>> outputParams=new ArrayList<>();
        outputParams.add(new TypeReference<Uint256>() {});
        List<Type>  types=TransactionFace.callContractViewMethod("0x3901952De2f16ad9B8646CF59C337d0b445A81Ca",contract,"decimals",list,outputParams);
        if (types!=null&&types.size()==1){
            /**
             * 0: uint256: amount 50000000000000000000
             */

            Uint256 amount= (Uint256)types.get(0);
            return amount.getValue();
        }
        return BigInteger.valueOf(18);
    }

    /**
     * 获取合约单位符号
     * @param contract
     * @return
     */
    public static String getSymbol(String contract){
        List<Type> list=new ArrayList<>();
        List<TypeReference<?>> outputParams=new ArrayList<>();
        outputParams.add(new TypeReference<Utf8String>() {});
        List<Type>  types=TransactionFace.callContractViewMethod("0x3901952De2f16ad9B8646CF59C337d0b445A81Ca",contract,"symbol",list,outputParams);
        if (types!=null&&types.size()==1){
            Utf8String amount= (Utf8String)types.get(0);
            return amount.getValue();
        }
        return null;
    }



    /**
     * 从注册合约获取全部的token
     */
    public static List<TokenInfo> getAllToken(String contract){
        List<Type> list=new ArrayList<>();
        List<TokenInfo> tokenInfos=new ArrayList<>();
        List<TypeReference<?>> outputParams=new ArrayList<>();
        outputParams.add(new TypeReference<DynamicArray<Address>>() {});
        List<Type>  types=TransactionFace.callContractViewMethod("0x3901952De2f16ad9B8646CF59C337d0b445A81Ca",contract,"getTokenList",list,outputParams);
        if (types!=null&&types.size()==1){
            Array hunterInfos= (Array)types.get(0);
            List<Address> ads=hunterInfos.getValue();
            ads.forEach(address -> {
                TokenInfo tokenInfo= getTokenDetail(contract,address.getValue());
                tokenInfos.add(tokenInfo);
            });

        }
        return tokenInfos;
    }

    /**
     * 获取通证详情
     * @param tokenAddress
     * @return
     */
    public static TokenInfo getTokenDetail(String contract,String tokenAddress){
        TokenInfo tokenInfo=new TokenInfo();
        List<Type> list=new ArrayList<>();
        List<TypeReference<?>> outputParams=new ArrayList<>();
        outputParams.add(new TypeReference<Address>() {});
        outputParams.add(new TypeReference<Address>() {});
        outputParams.add(new TypeReference<Utf8String>() {});
        outputParams.add(new TypeReference<Utf8String>() {});
        outputParams.add(new TypeReference<Uint256>() {});
        outputParams.add(new TypeReference<Utf8String>() {});
        outputParams.add(new TypeReference<Uint256>() {});
        outputParams.add(new TypeReference<Bool>() {});
        outputParams.add(new TypeReference<Address>() {});
        outputParams.add(new TypeReference<Address>() {});

        list.add(new Address(tokenAddress));
        List<Type>  types=TransactionFace.callContractViewMethod("0x3901952De2f16ad9B8646CF59C337d0b445A81Ca",contract,"getTokenInfo",list,outputParams);
        if (types!=null&&types.size()==10){
            /**
             * 0: address: _owner 0x3901952De2f16ad9B8646CF59C337d0b445A81Ca
             * 1: address: token 0x528b6083c0575a758b7AbA4B9785FD2c2aaCe581
             * 2: string: symbol HP
             * 3: string: icon http://jar-test.oss-cn-shenzhen.aliyuncs.com/soft/1.png
             * 4: uint256: len 18
             * 5: string: tokenName 和平积分
             * 6: uint256: sort 1
             * 7: bool : status true
             * 8: address: red 0x3901952De2f16ad9B8646CF59C337d0b445A81Ca
             * 9: address: pay 0x528b6083c0575a758b7AbA4B9785FD2c2aaCe581
             */
            Address owner= (Address)types.get(0);
            Address token= (Address)types.get(1);
            Utf8String symbol= (Utf8String)types.get(2);
            Utf8String icon= (Utf8String)types.get(3);
            Uint256 len= (Uint256)types.get(4);
            Utf8String tokenName= (Utf8String)types.get(5);
            Uint256 sort= (Uint256)types.get(6);
            Bool bool=(Bool)types.get(7);
            Address red= (Address)types.get(8);
            Address pay= (Address)types.get(9);
            tokenInfo.setStatus(bool.getValue());
            tokenInfo.setOwner(owner.getValue());
            tokenInfo.setIcon(icon.getValue());
            tokenInfo.setToken(token.getValue());
            tokenInfo.setSymbol(symbol.getValue());
            tokenInfo.setLen(len.getValue().intValue());
            tokenInfo.setTokenName(tokenName.getValue());
            tokenInfo.setSort(sort.getValue().intValue());
            tokenInfo.setRed(red.getValue());
            tokenInfo.setPay(pay.getValue());
            return tokenInfo;
        }
        return null;
    }


    /**
     * 用户别名注册费
     * @param contract
     * @return
     */
    public static BigInteger getRegisterUserFee(String contract){
        List<Type> list=new ArrayList<>();
        List<TypeReference<?>> outputParams=new ArrayList<>();
        outputParams.add(new TypeReference<Uint256>() {});
        List<Type>  types=TransactionFace.callContractViewMethod("0x3901952De2f16ad9B8646CF59C337d0b445A81Ca",contract,"registerUserFee",list,outputParams);
        if (types!=null&&types.size()==1){
            /**
             * 0: uint256: amount 50000000000000000000
             */

            Uint256 amount= (Uint256)types.get(0);
            return amount.getValue();
        }
        return BigInteger.valueOf(18);
    }
    /**
     * 通证注册费
     * @param contract
     * @return
     */
    public static BigInteger getRegisterTokenFee(String contract){
        List<Type> list=new ArrayList<>();
        List<TypeReference<?>> outputParams=new ArrayList<>();
        outputParams.add(new TypeReference<Uint256>() {});
        List<Type>  types=TransactionFace.callContractViewMethod("0x3901952De2f16ad9B8646CF59C337d0b445A81Ca",contract,"registerTokenFee",list,outputParams);
        if (types!=null&&types.size()==1){
            /**
             * 0: uint256: amount 50000000000000000000
             */

            Uint256 amount= (Uint256)types.get(0);
            return amount.getValue();
        }
        return BigInteger.valueOf(18);
    }
    /**
     * 红包开通费
     * @param contract
     * @return
     */
    public static BigInteger getRegisterRedFee(String contract){
        List<Type> list=new ArrayList<>();
        List<TypeReference<?>> outputParams=new ArrayList<>();
        outputParams.add(new TypeReference<Uint256>() {});
        List<Type>  types=TransactionFace.callContractViewMethod("0x3901952De2f16ad9B8646CF59C337d0b445A81Ca",contract,"registerRedFee",list,outputParams);
        if (types!=null&&types.size()==1){
            /**
             * 0: uint256: amount 50000000000000000000
             */

            Uint256 amount= (Uint256)types.get(0);
            return amount.getValue();
        }
        return BigInteger.valueOf(18);
    }
    /**
     * 支付开通费
     * @param contract
     * @return
     */
    public static BigInteger getRegisterPayFee(String contract){
        List<Type> list=new ArrayList<>();
        List<TypeReference<?>> outputParams=new ArrayList<>();
        outputParams.add(new TypeReference<Uint256>() {});
        List<Type>  types=TransactionFace.callContractViewMethod("0x3901952De2f16ad9B8646CF59C337d0b445A81Ca",contract,"registerPayFee",list,outputParams);
        if (types!=null&&types.size()==1){
            /**
             * 0: uint256: amount 50000000000000000000
             */

            Uint256 amount= (Uint256)types.get(0);
            return amount.getValue();
        }
        return BigInteger.valueOf(18);
    }


    public static String submitRegister(String contract,String priKey,String tokenAddr,String symbol,String tokenName,String icon,Integer len,BigInteger value){
        System.out.println("submitRegister");
        List<Type> params= Arrays.asList(new Address(tokenAddr),new Utf8String(symbol),new Utf8String(icon),new Utf8String(tokenName),new Uint(BigInteger.valueOf(len)));
        return TransactionFace.callContractFunctionOpValue(priKey,contract,params,"createToken",RedPackFace.GAS_LIMIT.toBigInteger(),RedPackFace.GAS_PRICE.toBigInteger(),value);
    }
    public static String submitRegisterRed(String contract,String priKey,String tokenAddr,String redContract,BigInteger value){
        System.out.println("submitRegister token");
        List<Type> params= Arrays.asList(new Address(redContract),new Address(tokenAddr));
        return TransactionFace.callContractFunctionOpValue(priKey,contract,params,"mapperRedContract",RedPackFace.GAS_LIMIT.toBigInteger(),RedPackFace.GAS_PRICE.toBigInteger(),value);
    }

    public static String submitRegisterPay(String contract,String priKey,String tokenAddr,String payContract,BigInteger value){
        System.out.println("submitRegister token");
        List<Type> params= Arrays.asList(new Address(payContract),new Address(tokenAddr));
        return TransactionFace.callContractFunctionOpValue(priKey,contract,params,"mapperPayContract",RedPackFace.GAS_LIMIT.toBigInteger(),RedPackFace.GAS_PRICE.toBigInteger(),value);
    }


    /**
     * 注册通证到通证合约
     * 这是个耗时的过程
     * //余额必须超过注册所需费用+手续费0.1
     * 注册默认18位
     */
    public static RegisterTokenDto registerToken(String centerContract,String pri,String amount,String symbol,String tokeName,String icon){
        Credentials credentials=Credentials.create(pri);
        RegisterTokenDto registerTokenDto=new RegisterTokenDto();
        try {
            BigDecimal balance=AccountFace.getMainCoinBalance(credentials.getAddress());
            BigInteger registerFee=CoinFace.getRegisterTokenFee(centerContract);
            BigDecimal needFee=new BigDecimal(registerFee).divide((BigDecimal.TEN.pow(18)),8,BigDecimal.ROUND_DOWN).add(new BigDecimal(0.1));
            if (needFee.compareTo(balance)>0){
                return RegisterTokenDto.buildError("余额不足，至少需要:"+needFee);
            }
            StandContract_sol_PubToken token=ContractUtil.createContract(pri,new BigInteger(amount).multiply((BigInteger.TEN.pow(18))),18,symbol,tokeName);
            if (token!=null&&StringUtils.isNotEmpty(token.getContractAddress())){

                CompletableFuture<String> futureSubmit = CompletableFuture.supplyAsync(()->{
                    return submitRegister(centerContract,pri,token.getContractAddress(),symbol,tokeName,icon,18,registerFee);
                });
                CompletableFuture<Boolean> future2 = futureSubmit.thenApply((p)->{
                    if (StringUtils.isEmpty(p)){
                        return false;
                    }
                    int tryCount=0;
                    boolean status=false;
                    while (tryCount<10&&!status){
                        tryCount++;
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("try count "+ tryCount);
                        status=TransactionFace.getTransactionStatus(p);
                    }
                    return status;
                });
                if (future2.join()){
                    registerTokenDto.setMsg("success");
                    registerTokenDto.setSuccess(true);
                    registerTokenDto.setContract(token.getContractAddress());
                }else {
                    registerTokenDto.setSuccess(false);
                    registerTokenDto.setContract(token.getContractAddress());
                    registerTokenDto.setMsg("注册失败");
                }
                return registerTokenDto;
            }
        }catch (Exception e){
            e.printStackTrace();
            return RegisterTokenDto.buildError("执行错误");
        }
        registerTokenDto.setSuccess(false);
        registerTokenDto.setContract("");
        registerTokenDto.setMsg("注册失败");
        return registerTokenDto;
    }



    public static RegisterTokenDto openRedPack(String centerContract,String pri,String coinContract){
        Credentials credentials=Credentials.create(pri);
        RegisterTokenDto registerTokenDto=new RegisterTokenDto();
        try {
            BigDecimal balance=AccountFace.getMainCoinBalance(credentials.getAddress());
            BigInteger registerFee=CoinFace.getRegisterRedFee(centerContract);
            BigDecimal needFee=new BigDecimal(registerFee).divide((BigDecimal.TEN.pow(18)),8,BigDecimal.ROUND_DOWN).add(new BigDecimal(0.1));
            if (needFee.compareTo(balance)>0){
                return RegisterTokenDto.buildError("余额不足，至少需要:"+needFee);
            }
            Redpack_sol_Redpack token=ContractUtil.createContractRed(pri,coinContract);
            if (token!=null&&StringUtils.isNotEmpty(token.getContractAddress())){

                CompletableFuture<String> futureSubmit = CompletableFuture.supplyAsync(()->{
                    return submitRegisterRed(centerContract,pri,coinContract,token.getContractAddress(),registerFee);
                });
                CompletableFuture<Boolean> future2 = futureSubmit.thenApply((p)->{
                    int tryCount=0;
                    boolean status=false;
                    while (tryCount<10&&!status){
                        tryCount++;
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("try count "+ tryCount);
                        status=TransactionFace.getTransactionStatus(p);
                    }
                    return status;
                });
                if (future2.join()){
                    registerTokenDto.setMsg("success");
                    registerTokenDto.setSuccess(true);
                    registerTokenDto.setContract(token.getContractAddress());
                }else {
                    registerTokenDto.setSuccess(false);
                    registerTokenDto.setContract(token.getContractAddress());
                    registerTokenDto.setMsg("创建失败");
                }
                return registerTokenDto;
            }
        }catch (Exception e){
            e.printStackTrace();
            return RegisterTokenDto.buildError("执行错误");
        }
        registerTokenDto.setSuccess(false);
        registerTokenDto.setContract("");
        registerTokenDto.setMsg("创建失败");
        return registerTokenDto;
    }


    public static RegisterTokenDto openPayCenter(String centerContract,String pri,String coinContract){
        Credentials credentials=Credentials.create(pri);
        RegisterTokenDto registerTokenDto=new RegisterTokenDto();
        try {
            BigDecimal balance=AccountFace.getMainCoinBalance(credentials.getAddress());
            BigInteger registerFee=CoinFace.getRegisterPayFee(centerContract);
            BigDecimal needFee=new BigDecimal(registerFee).divide((BigDecimal.TEN.pow(18)),8,BigDecimal.ROUND_DOWN).add(new BigDecimal(0.1));
            if (needFee.compareTo(balance)>0){
                return RegisterTokenDto.buildError("余额不足，至少需要:"+needFee);
            }
            PayCenter_sol_PayCenter token=ContractUtil.createContractPay(pri,coinContract,BigInteger.ZERO,BigInteger.ZERO);
            if (token!=null&&StringUtils.isNotEmpty(token.getContractAddress())){

                CompletableFuture<String> futureSubmit = CompletableFuture.supplyAsync(()->{
                    return submitRegisterPay(centerContract,pri,coinContract,token.getContractAddress(),registerFee);
                });
                CompletableFuture<Boolean> future2 = futureSubmit.thenApply((p)->{
                    int tryCount=0;
                    boolean status=false;
                    while (tryCount<10&&!status){
                        tryCount++;
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("try count "+ tryCount);
                        status=TransactionFace.getTransactionStatus(p);
                    }
                    return status;
                });
                if (future2.join()){
                    registerTokenDto.setMsg("success");
                    registerTokenDto.setSuccess(true);
                    registerTokenDto.setContract(token.getContractAddress());
                }else {
                    registerTokenDto.setSuccess(false);
                    registerTokenDto.setContract(token.getContractAddress());
                    registerTokenDto.setMsg("创建失败");
                }
                return registerTokenDto;
            }
        }catch (Exception e){
            e.printStackTrace();
            return RegisterTokenDto.buildError("执行错误");
        }
        registerTokenDto.setSuccess(false);
        registerTokenDto.setContract("");
        registerTokenDto.setMsg("创建失败");
        return registerTokenDto;
    }


    /**
     * 发布报告
     * @param
     */
    public static BaseMsg createReport(String contract, String priKey, String item, String json){
        List<Type> params= Arrays.asList(new Utf8String(item),new Utf8String(json));
        return BaseFace.dealMsg(TransactionFace.callContractFunctionOp(priKey,contract,params,"createReport",GAS_LIMIT.toBigInteger(),GAS_PRICE.toBigInteger()));
    }
    public static List<String> getLastReport(String contract,int size){
        List<Type> list=new ArrayList<>();
        list.add(new Uint(new BigInteger(size+"")));
        List<TypeReference<?>> outputParams=new ArrayList<>();
        outputParams.add(new TypeReference<DynamicArray<Utf8String>>() {});
        List<Type>  types=TransactionFace.callContractViewMethod("0x3901952De2f16ad9B8646CF59C337d0b445A81Ca",contract,"getLastReport",list,outputParams);
        if (types!=null&&types.size()==1){
            Array ows= (Array)types.get(0);
            return ows.getValue();
        }
        return new ArrayList<>();
    }
    /**
     * 发布新闻
     * @param
     */
    public static BaseMsg createNews(String contract, String priKey, String item, String json){
        List<Type> params= Arrays.asList(new Utf8String(item),new Utf8String(json));
        return BaseFace.dealMsg(TransactionFace.callContractFunctionOp(priKey,contract,params,"createNews",GAS_LIMIT.toBigInteger(),GAS_PRICE.toBigInteger()));
    }
    public static List<String> getLastNews(String contract,int size){
        List<Type> list=new ArrayList<>();
        list.add(new Uint(new BigInteger(size+"")));
        List<TypeReference<?>> outputParams=new ArrayList<>();
        outputParams.add(new TypeReference<DynamicArray<Utf8String>>() {});
        List<Type>  types=TransactionFace.callContractViewMethod("0x3901952De2f16ad9B8646CF59C337d0b445A81Ca",contract,"getLastNews",list,outputParams);
        if (types!=null&&types.size()==1){
            Array ows= (Array)types.get(0);
            return ows.getValue();
        }
        return new ArrayList<>();
    }
    /**
     * 发布公告
     * @param
     */
    public static BaseMsg createNotice(String contract, String priKey, String item, String json){
        List<Type> params= Arrays.asList(new Utf8String(item),new Utf8String(json));
        return BaseFace.dealMsg(TransactionFace.callContractFunctionOp(priKey,contract,params,"createNotice",GAS_LIMIT.toBigInteger(),GAS_PRICE.toBigInteger()));
    }
    public static List<String> getLastNotice(String contract,int size){
        List<Type> list=new ArrayList<>();
        list.add(new Uint(new BigInteger(size+"")));
        List<TypeReference<?>> outputParams=new ArrayList<>();
        outputParams.add(new TypeReference<DynamicArray<Utf8String>>() {});
        List<Type>  types=TransactionFace.callContractViewMethod("0x3901952De2f16ad9B8646CF59C337d0b445A81Ca",contract,"getLastNotice",list,outputParams);
        if (types!=null&&types.size()==1){
            Array ows= (Array)types.get(0);
            return ows.getValue();
        }
        return new ArrayList<>();
    }









    public static void main(String[] args) {
        EnvInstance.setEnv(new EnvBase("123.100.236.38"));
        String pri="e062469e7af68463161b1d3c314e206a6cf812c31ef2f436b1f7c1e84c26dd30";
        BigInteger amount=new BigInteger("10000000");
        Integer len=18;
        String symbol="COO";
        String tokenName="DDT";
        System.out.println(CoinFace.registerToken("0xb6bfa759f6e42d1074ed88d890eb4cae6f63431d",pri,new BigDecimal(amount).toPlainString(),symbol,tokenName,""));
    }

}
