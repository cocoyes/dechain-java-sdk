package com.dechain.utils;

import com.dechain.env.EnvBase;
import com.dechain.env.EnvInstance;
import com.dechain.msg.coin.BaseMsg;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;

import java.math.BigInteger;

public class ContractUtil {

    /**
     * 创建合约
     * @param priKey
     * @param amount
     * @param len
     * @param symbol
     * @param tokenName
     * @return
     */
    public static PubTokenSol createContract(String priKey, BigInteger amount, Integer len, String symbol, String tokenName){
        Web3j web3j= EnvInstance.getEnv().getWeb3j();
        Credentials credentials=Credentials.create(priKey);
        try {
            PubTokenSol token=
            PubTokenSol.
                    deploy(web3j,credentials, BaseMsg.GAS_PRICE.toBigInteger(),
                            BaseMsg.GAS_LIMIT.toBigInteger(),amount,tokenName,BigInteger.valueOf(len),symbol).send();

            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    public static GoodsSol createGoods(String priKey,String master,String payCoin,BigInteger num,BigInteger price){
        Web3j web3j= EnvInstance.getEnv().getWeb3j();
        Credentials credentials=Credentials.create(priKey);
        try {
            GoodsSol token=
                    GoodsSol.
                            deploy(web3j,credentials, BaseMsg.GAS_PRICE.toBigInteger(),
                                    BaseMsg.GAS_LIMIT.toBigInteger(),master,payCoin,num,price).send();

            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 创建红包合约
     * @param priKey
     * @return
     */
    public static RedpackSol createContractRed(String priKey, String coinTokenAddr){
        Web3j web3j= EnvInstance.getEnv().getWeb3j();
        Credentials credentials=Credentials.create(priKey);
        try {
            RedpackSol token=
                    RedpackSol.deploy(web3j,credentials, BaseMsg.GAS_PRICE.toBigInteger(),
                                    BaseMsg.GAS_LIMIT.toBigInteger(),coinTokenAddr).send();

            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 创建NFT合约
     * @param priKey
     * @return
     */
    public static NFTSol createContractNFT(String priKey, String tokenName, String symbol, String pic, String content){
        Web3j web3j= EnvInstance.getEnv().getWeb3j();
        Credentials credentials=Credentials.create(priKey);
        try {
            NFTSol token=
                    NFTSol.
                            deploy(web3j,credentials, BaseMsg.GAS_PRICE.toBigInteger(),
                                    BaseMsg.GAS_LIMIT.toBigInteger(),tokenName,symbol,pic,content).send();

            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 创建支付合约
     * @param priKey
     * @return
     */
    public static PayCenterSol createContractPay(String priKey, String coinTokenAddr, BigInteger keepAmount, BigInteger baseFee){
        Web3j web3j= EnvInstance.getEnv().getWeb3j();
        Credentials credentials=Credentials.create(priKey);
        try {
            PayCenterSol token=
                    PayCenterSol.
                            deploy(web3j,credentials, BaseMsg.GAS_PRICE.toBigInteger(),
                                    BaseMsg.GAS_LIMIT.toBigInteger(),coinTokenAddr,keepAmount,baseFee).send();

            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        EnvInstance.setEnv(new EnvBase("123.100.236.38"));
        String pri="e062469e7af68463161b1d3c314e206a6cf812c31ef2f436b1f7c1e84c26dd30";
        BigInteger amount=new BigInteger("10000000").multiply((BigInteger.TEN.pow(18)));
        Integer len=18;
        String symbol="COO";
        String tokenName="DDT";
        System.out.println(ContractUtil.createContract(pri,amount,len,symbol,tokenName).getContractAddress());
    }
}
