package com.dechain.utils;

import com.dechain.env.EnvBase;
import com.dechain.env.EnvInstance;
import com.dechain.face.RedPackFace;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tx.gas.ContractGasProvider;

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
    public static StandContract_sol_PubToken createContract(String priKey, BigInteger amount,Integer len,String symbol,String tokenName){
        Web3j web3j= EnvInstance.getEnv().getWeb3j();
        Credentials credentials=Credentials.create(priKey);
        try {
            StandContract_sol_PubToken token=
            StandContract_sol_PubToken.
                    deploy(web3j,credentials, RedPackFace.GAS_PRICE.toBigInteger(),
                            RedPackFace.GAS_LIMIT.toBigInteger(),amount,tokenName,BigInteger.valueOf(len),symbol).send();

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
    public static Redpack_sol_Redpack createContractRed(String priKey, String coinTokenAddr){
        Web3j web3j= EnvInstance.getEnv().getWeb3j();
        Credentials credentials=Credentials.create(priKey);
        try {
            Redpack_sol_Redpack token=
                    Redpack_sol_Redpack.
                            deploy(web3j,credentials, RedPackFace.GAS_PRICE.toBigInteger(),
                                    RedPackFace.GAS_LIMIT.toBigInteger(),coinTokenAddr).send();

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
    public static PayCenter_sol_PayCenter createContractPay(String priKey, String coinTokenAddr,BigInteger keepAmount,BigInteger baseFee){
        Web3j web3j= EnvInstance.getEnv().getWeb3j();
        Credentials credentials=Credentials.create(priKey);
        try {
            PayCenter_sol_PayCenter token=
                    PayCenter_sol_PayCenter.
                            deploy(web3j,credentials, RedPackFace.GAS_PRICE.toBigInteger(),
                                    RedPackFace.GAS_LIMIT.toBigInteger(),coinTokenAddr,keepAmount,baseFee).send();

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
