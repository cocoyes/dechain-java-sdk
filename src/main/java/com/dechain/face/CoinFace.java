package com.dechain.face;

import com.alibaba.fastjson.JSON;
import com.dechain.env.EnvBase;
import com.dechain.env.EnvInstance;
import com.dechain.msg.coin.*;
import com.dechain.utils.*;

import com.dechain.utils.PubTokenSol;

import com.dechain.utils.crypto.Crypto;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.*;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.ReadonlyTransactionManager;
import org.web3j.utils.Numeric;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;

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
     * 严格执行，没有返回长度0，用于ERC721与ERC20区分
     * @param contract
     * @return
     */
    public static BigInteger getDecimalMust(String contract){
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
        return BigInteger.valueOf(0);
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

    public static String getOwner(String contract){
        List<Type> list=new ArrayList<>();
        List<TypeReference<?>> outputParams=new ArrayList<>();
        outputParams.add(new TypeReference<Address>() {});
        List<Type>  types=TransactionFace.callContractViewMethod("0x3901952De2f16ad9B8646CF59C337d0b445A81Ca",contract,"owner",list,outputParams);
        if (types!=null&&types.size()==1){
            Address address= (Address)types.get(0);
            return address.getValue();
        }
        return null;
    }


    public static String getName(String contract){
        List<Type> list=new ArrayList<>();
        List<TypeReference<?>> outputParams=new ArrayList<>();
        outputParams.add(new TypeReference<Utf8String>() {});
        List<Type>  types=TransactionFace.callContractViewMethod("0x3901952De2f16ad9B8646CF59C337d0b445A81Ca",contract,"name",list,outputParams);
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
     * 从注册合约获取全部的token
     */
    public static List<Address> getAllTokenArray(String contract){
        List<Type> list=new ArrayList<>();
        List<TypeReference<?>> outputParams=new ArrayList<>();
        outputParams.add(new TypeReference<DynamicArray<Address>>() {});
        List<Type>  types=TransactionFace.callContractViewMethod("0x3901952De2f16ad9B8646CF59C337d0b445A81Ca",contract,"getTokenList",list,outputParams);
        if (types!=null&&types.size()==1){
            Array hunterInfos= (Array)types.get(0);
            List<Address> ads=hunterInfos.getValue();
            return ads;

        }
        return new ArrayList<>();
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
        outputParams.add(new TypeReference<Uint256>() {});
        list.add(new Address(tokenAddress));
        List<Type>  types=TransactionFace.callContractViewMethod("0x3901952De2f16ad9B8646CF59C337d0b445A81Ca",contract,"getTokenInfo",list,outputParams);
        if (types!=null&&types.size()==11){
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
            Uint256 ctype= (Uint256)types.get(10);
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
            tokenInfo.setCtype(ctype.getValue().intValue());
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
     * 获取注册基本信息
     * @param contract
     * @return
     */
    public static RegisterBaseInfo getBaseInfo(String contract){
        List<Type> list=new ArrayList<>();
        List<TypeReference<?>> outputParams=new ArrayList<>();
        outputParams.add(new TypeReference<Uint256>() {});
        outputParams.add(new TypeReference<Uint256>() {});
        outputParams.add(new TypeReference<Uint256>() {});
        outputParams.add(new TypeReference<Uint256>() {});
        outputParams.add(new TypeReference<Uint256>() {});
        outputParams.add(new TypeReference<Uint256>() {});
        List<Type>  types=TransactionFace.callContractViewMethod("0x3901952De2f16ad9B8646CF59C337d0b445A81Ca",contract,"getBaseInfo",list,outputParams);
        if (types!=null&&types.size()==6){
            /**
             * 0: uint256: amount 50000000000000000000
             */

            Uint256 registerTokenFee= (Uint256)types.get(0);
            Uint256 registerRedFee= (Uint256)types.get(1);
            Uint256 registerPayFee= (Uint256)types.get(2);
            Uint256 openContractCount= (Uint256)types.get(3);
            Uint256 openRedCount= (Uint256)types.get(4);
            Uint256 openPayCount= (Uint256)types.get(5);
            RegisterBaseInfo registerBaseInfo=new RegisterBaseInfo();
            registerBaseInfo.setRegisterTokenFee(registerTokenFee.getValue());
            registerBaseInfo.setRegisterRedFee(registerRedFee.getValue());
            registerBaseInfo.setRegisterPayFee(registerPayFee.getValue());
            registerBaseInfo.setOpenContractCount(openContractCount.getValue());
            registerBaseInfo.setOpenRedCount(openRedCount.getValue());
            registerBaseInfo.setOpenPayCount(openPayCount.getValue());
            return registerBaseInfo;
        }
        return null;
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
     * NFT开通费
     * @param contract
     * @return
     */
    public static BigInteger getRegisterNFTFee(String contract){
        List<Type> list=new ArrayList<>();
        List<TypeReference<?>> outputParams=new ArrayList<>();
        outputParams.add(new TypeReference<Uint256>() {});
        List<Type>  types=TransactionFace.callContractViewMethod("0x3901952De2f16ad9B8646CF59C337d0b445A81Ca",contract,"nftPublishFee",list,outputParams);
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


    public static String submitRegister(String contract,String priKey,String tokenAddr,String symbol,String tokenName,String icon,Integer len,BigInteger value,int ctype){
        System.out.println("submitRegister");
        List<Type> params= Arrays.asList(new Address(tokenAddr),new Utf8String(symbol),new Utf8String(icon),new Utf8String(tokenName),new Uint(BigInteger.valueOf(len)),new Uint(BigInteger.valueOf(ctype)));
        return TransactionFace.callContractFunctionOpValue(priKey,contract,params,"createToken",BaseMsg.GAS_LIMIT.toBigInteger(),BaseMsg.GAS_PRICE.toBigInteger(),value);
    }
    public static String submitRegisterRed(String contract,String priKey,String tokenAddr,String redContract,BigInteger value){
        System.out.println("submitRegister token");
        List<Type> params= Arrays.asList(new Address(redContract),new Address(tokenAddr));
        return TransactionFace.callContractFunctionOpValue(priKey,contract,params,"mapperRedContract",BaseMsg.GAS_LIMIT.toBigInteger(),BaseMsg.GAS_PRICE.toBigInteger(),value);
    }

    public static String submitRegisterNFT(String contract,String priKey,String nftContract,String tokenName,String pic,String content,BigInteger value){
        System.out.println("submitRegister NFT");
        //address _contractAddr,string memory _img,string memory _title,string memory _content
        List<Type> params= Arrays.asList(new Address(nftContract),new Utf8String(pic),new Utf8String(tokenName),new Utf8String(content));
        return TransactionFace.callContractFunctionOpValue(priKey,contract,params,"createNFT",BaseMsg.GAS_LIMIT.toBigInteger(),BaseMsg.GAS_PRICE.toBigInteger(),value);
    }

    public static String submitRegisterPay(String contract,String priKey,String tokenAddr,String payContract,BigInteger value){
        System.out.println("submitRegister token");
        List<Type> params= Arrays.asList(new Address(payContract),new Address(tokenAddr));
        return TransactionFace.callContractFunctionOpValue(priKey,contract,params,"mapperPayContract",BaseMsg.GAS_LIMIT.toBigInteger(),BaseMsg.GAS_PRICE.toBigInteger(),value);
    }


    /**
     * 注册通证到通证合约
     * 这是个耗时的过程
     * //余额必须超过注册所需费用+手续费0.1
     * 注册默认18位
     */
    public static RegisterTokenDto registerToken(String centerContract,String pri,String amount,String symbol,String tokeName,String icon,int ctype){
        Credentials credentials=Credentials.create(pri);
        RegisterTokenDto registerTokenDto=new RegisterTokenDto();
        try {
            BigDecimal balance=AccountFace.getMainCoinBalance(credentials.getAddress());
            BigInteger registerFee=CoinFace.getRegisterTokenFee(centerContract);
            BigDecimal needFee=new BigDecimal(registerFee).divide((BigDecimal.TEN.pow(18)),8,BigDecimal.ROUND_DOWN).add(new BigDecimal(0.1));
            if (needFee.compareTo(balance)>0){
                return RegisterTokenDto.buildError("余额不足，至少需要:"+needFee);
            }
            PubTokenSol token=ContractUtil.createContract(pri,new BigInteger(amount).multiply((BigInteger.TEN.pow(18))),18,symbol,tokeName);
            if (token!=null&&StringUtils.isNotEmpty(token.getContractAddress())){

                CompletableFuture<String> futureSubmit = CompletableFuture.supplyAsync(()->{
                    return submitRegister(centerContract,pri,token.getContractAddress(),symbol,tokeName,icon,18,registerFee,ctype);
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
            RedpackSol token=ContractUtil.createContractRed(pri,coinContract);
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
            PayCenterSol token=ContractUtil.createContractPay(pri,coinContract,BigInteger.ZERO,BigInteger.ZERO);
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
    public static BaseMsg createReport(String contract, String priKey, String item, String md5){
        List<Type> params= Arrays.asList(new Utf8String(item),new Utf8String(md5));
        return BaseFace.dealMsg(TransactionFace.callContractFunctionOp(priKey,contract,params,"createReport",GAS_LIMIT.toBigInteger(),GAS_PRICE.toBigInteger()));
    }
    public static List<String> getLastReport(String contract,int size){
        List<Type> list=new ArrayList<>();
        list.add(new Uint8(new BigInteger(size+"")));
        List<TypeReference<?>> outputParams=new ArrayList<>();
        outputParams.add(new TypeReference<DynamicArray<Utf8String>>() {});
        List<Type>  types=TransactionFace.callContractViewMethod("0x3901952De2f16ad9B8646CF59C337d0b445A81Ca",contract,"getLastReportItem",list,outputParams);
        if (types!=null&&types.size()==1){
            Array ows= (Array)types.get(0);
            List<Utf8String> ls= ows.getValue();
            List<String> rs=new ArrayList<>();
            for(Utf8String str:ls){
                rs.add(str.getValue());
            }
            return rs;
        }
        return new ArrayList<>();
    }
    /**
     * 发布新闻
     * @param
     */
    public static BaseMsg createNews(String contract, String priKey,  String md5){
        List<Type> params= Arrays.asList(new Utf8String(md5),new Utf8String(md5));
        return BaseFace.dealMsg(TransactionFace.callContractFunctionOp(priKey,contract,params,"createNews",GAS_LIMIT.toBigInteger(),GAS_PRICE.toBigInteger()));
    }
    private static  List<String> convertToNative(List<Type> arr) {
        List<List<String>> out = new ArrayList();
        Iterator<Type> it = arr.iterator();

        while(it.hasNext()) {
            out.add((List<String>)it.next().getValue());
        }

        return out.get(0);
    }
    public static List<String> getLastNews(String contract,int size){
        List<Type> list=new ArrayList<>();
        list.add(new Uint8(new BigInteger(size+"")));
        List<TypeReference<?>> outputParams=Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Utf8String>>() {});
        List<Type>  types=TransactionFace.callContractViewMethod("0x3901952De2f16ad9B8646CF59C337d0b445A81Ca",contract,"getLastNews",list,outputParams);

        return convertToNative(types);
    }
    public static String getNews(String contract,String item){
        List<Type> list=new ArrayList<>();
        list.add(new Utf8String(item));
        List<TypeReference<?>> outputParams=new ArrayList<>();
        outputParams.add(new TypeReference<Utf8String>() {});
        List<Type>  types=TransactionFace.callContractViewMethod("0x3901952De2f16ad9B8646CF59C337d0b445A81Ca",contract,"getNews",list,outputParams);
        if (types!=null&&types.size()==1){
            Utf8String ows= (Utf8String)types.get(0);
            return ows.getValue();
        }
        return "";
    }
    /**
     * 发布公告
     * @param
     */
    public static BaseMsg createNotice(String contract, String priKey, String md5){
        List<Type> params= Arrays.asList(new Utf8String(md5),new Utf8String(md5));
        return BaseFace.dealMsg(TransactionFace.callContractFunctionOp(priKey,contract,params,"createNotice",GAS_LIMIT.toBigInteger(),GAS_PRICE.toBigInteger()));
    }
    public static List<String> getLastNotice(String contract,int size){
        List<Type> list=new ArrayList<>();
        list.add(new Uint8(new BigInteger(size+"")));
        List<TypeReference<?>> outputParams=new ArrayList<>();
        outputParams.add(new TypeReference<DynamicArray<Utf8String>>() {});
        List<Type>  types=TransactionFace.callContractViewMethod("0x3901952De2f16ad9B8646CF59C337d0b445A81Ca",contract,"getLastNotice",list,outputParams);
        if (types!=null&&types.size()==1){
            Array ows= (Array)types.get(0);
            return ows.getValue();
        }
        return new ArrayList<>();
    }


    /**
     * 获取企业基本信息
     * @param tokenContract
     * @return
     */
    public static CompanyInfo getCompanyInfo(String tokenContract){
        List<Type> list=new ArrayList<>();
        List<TypeReference<?>> outputParams=new ArrayList<>();
        outputParams.add(new TypeReference<Utf8String>() {});
        List<Type>  types=TransactionFace.callContractViewMethod("0x3901952De2f16ad9B8646CF59C337d0b445A81Ca",tokenContract,"companyInfo",list,outputParams);
        if (types!=null&&types.size()==1){
            Utf8String ows= (Utf8String)types.get(0);
            String str=ows.getValue();
            CompanyInfo companyInfo=JSON.parseObject(str,CompanyInfo.class);
            return companyInfo;
        }
        return new CompanyInfo();
    }

    /**
     * 更新企业信息
     * @param contract
     * @param priKey
     * @param json
     * @return
     */
    public static BaseMsg updateCompanyInfo(String contract, String priKey, String json){
        List<Type> params= Arrays.asList(new Utf8String(json));
        return BaseFace.dealMsg(TransactionFace.callContractFunctionOp(priKey,contract,params,"updateCompanyInfo",GAS_LIMIT.toBigInteger().multiply(BigInteger.TEN.multiply(BigInteger.TEN)),GAS_PRICE.toBigInteger()));
    }


    /**
     * 通过hash返回合约地址、消息摘要
     * type 0 新闻、1公告、2财报
     */
    public static CompanyMsgInfo getCompanyMsgInfo(String hash,int type){
        CompanyMsgInfo companyMsgInfo=new CompanyMsgInfo();
        TransactionReceipt receipt=TransactionFace.getTransactionDetail(hash);
        if (receipt==null){
            return null;
        }else {
            companyMsgInfo.setContract(receipt.getTo());
            companyMsgInfo.setHash(hash);
            companyMsgInfo.setFrom(receipt.getFrom());
            Web3j web3j= EnvInstance.getEnv().getWeb3j();
            ReadonlyTransactionManager transactionManager=new ReadonlyTransactionManager(web3j,"0x3901952De2f16ad9B8646CF59C337d0b445A81Ca");
            PubTokenSol pubToken= PubTokenSol.load(receipt.getTo(),web3j,transactionManager,BaseMsg.GAS_PRICE.toBigInteger(),
                    BaseMsg.GAS_LIMIT.toBigInteger());
            if(type==0){
                String symbol1="";
                try {
                    symbol1=pubToken.symbol().send();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                List<PubTokenSol.CreateNewsEventEventResponse> responses=pubToken.getCreateNewsEventEvents(receipt);
                if(responses!=null&&responses.size()>0){
                    String _item=responses.get(0)._item;
                    companyMsgInfo.setSign(_item);
                }
            }else if(type==1){
                List<PubTokenSol.CreateNoticeEventEventResponse> responses=pubToken.getCreateNoticeEventEvents(receipt);
                if(responses!=null&&responses.size()>0){
                    String _item=responses.get(0)._item;
                    companyMsgInfo.setSign(_item);
                }
            }else if(type==2){
                List<PubTokenSol.CreateReportEventEventResponse> responses=pubToken.getCreateReportEventEvents(receipt);
                if(responses!=null&&responses.size()>0){
                    String _item=responses.get(0)._json;
                    companyMsgInfo.setSign(_item);
                }
            }else {
                return null;
            }
            return companyMsgInfo;
        }

    }


    /**
     * 发行NFT合约
     */
    public static RegisterTokenDto openNFT(String centerContract,String pri,String tokenName,String symbol,String pic,String content){
        Credentials credentials=Credentials.create(pri);
        RegisterTokenDto registerTokenDto=new RegisterTokenDto();
        try {
            BigDecimal balance=AccountFace.getMainCoinBalance(credentials.getAddress());
            BigInteger registerFee=CoinFace.getRegisterNFTFee(centerContract);
            BigDecimal needFee=new BigDecimal(registerFee).divide((BigDecimal.TEN.pow(18)),8,BigDecimal.ROUND_DOWN).add(new BigDecimal(0.1));
            if (needFee.compareTo(balance)>0){
                return RegisterTokenDto.buildError("余额不足，至少需要:"+needFee);
            }
            NFTSol token= ContractUtil.createContractNFT(pri,tokenName,symbol,pic,content);
            if (token!=null&& StringUtils.isNotEmpty(token.getContractAddress())){

                CompletableFuture<String> futureSubmit = CompletableFuture.supplyAsync(()->{
                    return submitRegisterNFT(centerContract,pri,token.getContractAddress(),tokenName,pic,content,registerFee);
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
     * 绑定银行卡
     * @param
     */
    public static BaseMsg updateBankInfo(String contract, String priKey, String bankNo){
        String data=MD5Util.encode(bankNo);
        List<Type> params= Arrays.asList(new Utf8String(data));
        BaseMsg baseMsg=BaseFace.dealMsg(TransactionFace.callContractFunctionOp(priKey,contract,params,"setBankItem",GAS_LIMIT.toBigInteger(),GAS_PRICE.toBigInteger()));
        if(baseMsg.isSuccess()){
            baseMsg.setData(data);
        }
        return baseMsg;
    }

    /**
     * 根据地址，获取银行卡号的签名
     * @param contract
     * @return
     */
    public static String getBankNoSignByAddress(String contract,String address){
        List<Type> params= Arrays.asList(new Address(address));
        List<TypeReference<?>> outputParams=new ArrayList<>();
        outputParams.add(new TypeReference<Utf8String>() {});
        List<Type>  types=TransactionFace.callContractViewMethod("0x3901952De2f16ad9B8646CF59C337d0b445A81Ca",contract,"getBankItemMy",params,outputParams);
        if (types!=null&&types.size()==1){
            Utf8String amount= (Utf8String)types.get(0);
            return amount.getValue();
        }
        return null;
    }





    public static void main(String[] args) {
        EnvInstance.setEnv(new EnvBase("192.168.6.42"));
        System.out.println(CoinFace.getLastReport("0xd85a0CA5a84196cb3137fEafDC8b01AeC17cEbA8",5));
    }


}
