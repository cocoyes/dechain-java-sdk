package com.dechain.face;


import com.dechain.env.EnvBase;
import com.dechain.env.EnvInstance;

import org.apache.commons.lang3.StringUtils;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * 签名类接口
 */
public class TransactionFace {

    private static final BigInteger ETH_MAX_GAS=BigInteger.valueOf(21000);
    private static final BigInteger CONTRACT_MAX_GAS=BigInteger.valueOf(100000);

    public static BigInteger getCommonGasPrice() {
        Web3j web3j=EnvInstance.getEnv().getWeb3j();
        EthGasPrice gasPrice = null;
        try {
            gasPrice = web3j.ethGasPrice().send();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BigInteger baseGasPrice =  gasPrice.getGasPrice();
        return baseGasPrice;
    }




    //获取主链推荐手续费
    public static BigDecimal getEthFee(){
        BigInteger gasPrice= getCommonGasPrice();
        return new BigDecimal(gasPrice).multiply(new BigDecimal(ETH_MAX_GAS)).divide((BigDecimal.TEN.pow(18))).setScale(8,BigDecimal.ROUND_DOWN);
    }




    //获取合约推荐手续费
    public static BigDecimal getContractFee(){
        BigInteger gasPrice= getCommonGasPrice();
        return new BigDecimal(gasPrice).multiply(new BigDecimal(CONTRACT_MAX_GAS)).divide((BigDecimal.TEN.pow(18))).setScale(8,BigDecimal.ROUND_DOWN);
    }


    /**
     * 查询交易是否完成等信息
     */
    public static boolean getTransactionStatus(String hash){
        TransactionReceipt transactionReceipt=getTransactionDetail(hash);
        if (transactionReceipt!=null&&transactionReceipt.getStatus().equalsIgnoreCase("0x1")){
            return true;
        }
        return false;
    }


    /**
     * 获取交易详情
     * @param hash
     * @return
     *
     * String transactionH
     * String transactionI
     * String blockHash;
     * String blockNumber;
     * String cumulativeGa
     * String gasUsed;
     * String contractAddr
     * String root;
     * String status;  状态
     * String from;
     * String to;
     * List<Log> logs; 日志（重要！！！）
     * String logsBloom;
     */
    public static TransactionReceipt getTransactionDetail(String hash){
        Web3j web3j=EnvInstance.getEnv().getWeb3j();
        Optional<TransactionReceipt> optional=null;
        try {
            optional= web3j.ethGetTransactionReceipt(hash).send().getTransactionReceipt();

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (optional!=null&&optional.isPresent()){
            return optional.get();
        }
        return null;
    }


    /**
     * 发送普通交易
     * @param priKey
     * @param amount
     * @param toAddress
     * @return
     */
    public static String sendCommonTrans(String priKey,String amount,String toAddress){
        if (StringUtils.isEmpty(toAddress)){
            System.out.println("to addr is null");
            return null;
        }
        if (!toAddress.startsWith("0x")){
            toAddress= AccountFace.addressToHex(toAddress);
        }
        String hexValue=commonTransSign(priKey,amount,toAddress);
        Web3j web3j=EnvInstance.getEnv().getWeb3j();
        EthSendTransaction ethSendTransaction = null;
        try {
            ethSendTransaction = web3j.ethSendRawTransaction(hexValue).sendAsync().get();
            String transactionHash = ethSendTransaction.getTransactionHash();
            System.out.println("txid ="+transactionHash );
            return transactionHash;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 发送合约交易
     * @param priKey
     * @param amount
     * @param toAddress
     * @return
     */
    public static String sendContractTrans(String priKey,String contract,String amount,String toAddress){
        if (StringUtils.isEmpty(toAddress)){
            System.out.println("to addr is null");
            return null;
        }
        if (!toAddress.startsWith("0x")){
            toAddress= AccountFace.addressToHex(toAddress);
        }
        String hexValue=contract20Sign(priKey,contract,amount,toAddress);
        Web3j web3j=EnvInstance.getEnv().getWeb3j();
        EthSendTransaction ethSendTransaction = null;
        try {
            ethSendTransaction = web3j.ethSendRawTransaction(hexValue).sendAsync().get();
            String transactionHash = ethSendTransaction.getTransactionHash();
            System.out.println("txid ="+transactionHash );
            return transactionHash;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 普通交易签名
     */
    public static String commonTransSign(String priKey,String amount,String toAddress){
        if (StringUtils.isEmpty(toAddress)){
            System.out.println("to addr is null");
            return null;
        }
        if (!toAddress.startsWith("0x")){
            toAddress= AccountFace.addressToHex(toAddress);
        }
        Credentials credentials=Credentials.create(priKey);
        Web3j web3j=EnvInstance.getEnv().getWeb3j();
        try {
            EthGetTransactionCount ethGetTransactionCount =web3j.ethGetTransactionCount(credentials.getAddress(), DefaultBlockParameterName.LATEST)
                    .sendAsync()
                    .get();

            BigInteger nonce = ethGetTransactionCount.getTransactionCount();
            BigInteger gasPrice = getCommonGasPrice();
            BigInteger value = Convert.toWei(amount, Convert.Unit.ETHER).toBigInteger();

            BigInteger maxGas = new BigInteger("21000");
            System.out.println("value="+value+",gasPrice="+gasPrice+",gasLimit="+maxGas+",nonce="+nonce+",address="+toAddress);
            RawTransaction rawTransaction = RawTransaction.createEtherTransaction(
                    nonce, gasPrice, maxGas, toAddress, value);

            byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
            String hexValue = Numeric.toHexString(signedMessage);
            return hexValue;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String commonTransSignByNonce(String priKey,String amount,String toAddress,BigInteger nonce){
        if (StringUtils.isEmpty(toAddress)){
            System.out.println("to addr is null");
            return null;
        }
        if (!toAddress.startsWith("0x")){
            toAddress= AccountFace.addressToHex(toAddress);
        }
        Credentials credentials=Credentials.create(priKey);
        Web3j web3j=EnvInstance.getEnv().getWeb3j();
        try {
            EthGetTransactionCount ethGetTransactionCount =web3j.ethGetTransactionCount(credentials.getAddress(), DefaultBlockParameterName.LATEST)
                    .sendAsync()
                    .get();

            BigInteger gasPrice = getCommonGasPrice();
            BigInteger value = Convert.toWei(amount, Convert.Unit.ETHER).toBigInteger();

            BigInteger maxGas = new BigInteger("21000");
            System.out.println("value="+value+",gasPrice="+gasPrice+",gasLimit="+maxGas+",nonce="+nonce+",address="+toAddress);
            RawTransaction rawTransaction = RawTransaction.createEtherTransaction(
                    nonce, gasPrice, maxGas, toAddress, value);

            byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
            String hexValue = Numeric.toHexString(signedMessage);
            return hexValue;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 合约交易签名
     */
    public static String contract20Sign(String priKey,String contractAddress,String amount,String toAddress){
        if (StringUtils.isEmpty(toAddress)){
            System.out.println("to addr is null");
            return null;
        }
        if (!toAddress.startsWith("0x")){
            toAddress= AccountFace.addressToHex(toAddress);
        }
        Credentials credentials=Credentials.create(priKey);
        Web3j web3j=EnvInstance.getEnv().getWeb3j();
        try {
            EthGetTransactionCount ethGetTransactionCount =web3j.ethGetTransactionCount(credentials.getAddress(), DefaultBlockParameterName.LATEST)
                    .sendAsync()
                    .get();
            BigInteger nonce = ethGetTransactionCount.getTransactionCount();
            //BigInteger gasPrice = ethService.getGasPrice();
            BigInteger gasPrice=getCommonGasPrice();
            BigInteger value = new BigInteger(amount);
            Function fn = new Function("transfer", Arrays.asList(new Address(toAddress), new Uint256(value)), Collections.<TypeReference<?>>emptyList());
            String data = FunctionEncoder.encode(fn);
            System.out.println("value="+value+",gasPrice="+gasPrice+",gasLimit="+CONTRACT_MAX_GAS+",nonce="+nonce+",address="+toAddress+"contract="+contractAddress);
            RawTransaction rawTransaction = RawTransaction.createTransaction(
                    nonce, gasPrice, CONTRACT_MAX_GAS, contractAddress, data);
            byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
            String hexValue = Numeric.toHexString(signedMessage);
            System.out.println("hexRawValue ="+hexValue );
            return hexValue;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 调用指定合约函数,此方法是调用操作合约的方法，需要消耗gas
     * 函数名，参数列表
     */
    public static String callContractFunctionOp(String priKey, String contractAddress,
                                                      List<Type> inputParams,
                                                      String funcName, BigInteger maxGas, BigInteger gasPrice){
        List<TypeReference<?>> result=new ArrayList<>();
        Credentials credentials=Credentials.create(priKey);
        Web3j web3j=EnvInstance.getEnv().getWeb3j();
        try {
            EthGetTransactionCount ethGetTransactionCount =web3j.ethGetTransactionCount(credentials.getAddress(), DefaultBlockParameterName.LATEST)
                    .sendAsync()
                    .get();
            BigInteger nonce = ethGetTransactionCount.getTransactionCount();
            Function fn = new Function(funcName, inputParams,result);
            String data = FunctionEncoder.encode(fn);
            RawTransaction rawTransaction = RawTransaction.createTransaction(
                    nonce, gasPrice, maxGas, contractAddress, data);
            byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
            String hexValue = Numeric.toHexString(signedMessage);
            System.out.println("hexRawValue ="+hexValue );
            EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(hexValue).sendAsync().get();
            String transactionHash = ethSendTransaction.getTransactionHash();
            System.out.println("txid ="+transactionHash );
            return transactionHash;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 调用指定合约函数,此方法是调用操作合约的方法，需要消耗gas
     * 函数名，参数列表
     * 附带value
     */
    public static String callContractFunctionOpValue(String priKey, String contractAddress,
                                                List<Type> inputParams,
                                                String funcName, BigInteger maxGas, BigInteger gasPrice,BigInteger value){
        List<TypeReference<?>> result=new ArrayList<>();
        Credentials credentials=Credentials.create(priKey);
        Web3j web3j=EnvInstance.getEnv().getWeb3j();
        try {
            EthGetTransactionCount ethGetTransactionCount =web3j.ethGetTransactionCount(credentials.getAddress(), DefaultBlockParameterName.LATEST)
                    .sendAsync()
                    .get();
            BigInteger nonce = ethGetTransactionCount.getTransactionCount();
            Function fn = new Function(funcName, inputParams,result);
            String data = FunctionEncoder.encode(fn);
            RawTransaction rawTransaction = RawTransaction.createTransaction(
                    nonce, gasPrice, maxGas, contractAddress,value, data);
            byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
            String hexValue = Numeric.toHexString(signedMessage);
            System.out.println("hexRawValue ="+hexValue );
            EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(hexValue).sendAsync().get();
            String transactionHash = ethSendTransaction.getTransactionHash();
            System.out.println("txid ="+transactionHash +",result="+ethSendTransaction.getResult());
            return transactionHash;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 无需消耗gas的合约调用，仅限支持view的方法
     */
    public static List<Type> callContractViewMethod(String from,String contract,String method,List<Type> inputParams,List<TypeReference<?>> outputParams){
        Web3j web3j=EnvInstance.getEnv().getWeb3j();

        Function function = new Function(
                method,
                inputParams,
                outputParams);

        String encodedFunction = FunctionEncoder.encode(function);
        EthCall response = null;
        try {
            response = web3j.ethCall(
                    org.web3j.protocol.core.methods.request.Transaction.createEthCallTransaction(from,contract, encodedFunction),
            DefaultBlockParameterName.LATEST).sendAsync().get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (response!=null){
            List<Type> someTypes = FunctionReturnDecoder.decode(
                    response.getValue(), function.getOutputParameters());
            return someTypes;
        }else {
            return null;
        }
    }



    /**
     * 计算手续费
     * @param args
     */



    public static void main(String[] args) {
        EnvInstance.setEnv(new EnvBase("192.168.6.42"));
        Web3j web3j=EnvInstance.getEnv().getWeb3j();



        //8e7e58980456096f748dfd8a221f35d3951dc48e79aad91197a744563c0edc9e
        String priKey="602d17e7a1bf0e1fb6b9c43ffff1908fb8dc82a3e454d3b7df627b963e8e25fc";
        String toAddr="0x8e41AB0D1C260c3632b973934d94CE7307DB0Ded";

        System.out.println("old :=799.8999579997217893 \t balance:"+AccountFace.getMainCoinBalance("0x02382e262c19138e7b8cd4725abd4aa921b77bc4"));

        System.out.println(new BigDecimal("799.8999579997217893").subtract(new BigDecimal("749.3499579997213693")));

        //发送普通交易
        System.out.println("-----------------发送普通交易---------------------------");
        TransactionFace.sendCommonTrans(priKey,"50.55",toAddr);
        //System.out.println("balance:"+AccountFace.getMainCoinBalance("0x02382e262c19138e7b8cd4725abd4aa921b77bc4"));
        System.out.println("-----------------发送合约交易---------------------------");
        String contractAddress="0x6ffd23b944a2075fcffe2de1d66067092269645e";
        BigInteger am=new BigInteger("5000").multiply(BigInteger.TEN.pow(18));
      // TransactionFace.sendContractTrans(priKey,contractAddress,am.toString(),toAddr);
       /* Optional<TransactionReceipt> optional=null;
        try {
            optional= web3j.ethGetTransactionReceipt("0x18a10440be0dbd004bc5bb4444404ec6f7faccfa023f61a0d0add8c403fa470e").send().getTransactionReceipt();
            System.out.println(optional.get().getStatus());
        } catch (IOException e) {
            e.printStackTrace();
        }*/

       //TransactionFace.getTransactionStatus("0x5951546733eab67d73f996d81b49158008a8c2c4e854703044841837ae1817f1");

    }





    public static void testMethodSign(){
        System.out.println(FunctionEncoder.encode(new Function("redpackCreated",new ArrayList<>(),new ArrayList<>())));
    }





}
