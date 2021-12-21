package com.dechain.face;

import com.alibaba.fastjson.JSON;
import com.dechain.msg.coin.*;
import com.dechain.utils.ContractUtil;
import com.dechain.utils.Redpack_sol_Redpack;
import com.dechain.utils.crypto.Crypto;
import org.apache.commons.lang3.StringUtils;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class NFTFace {


    /**
     * 获取NFT市场已上架的NFT图集列表
     * @param registerContract
     * @return
     */
    public static List<NFTMarketItem> getNFTMarketSimpleItem(String registerContract){
        List<Type> list=new ArrayList<>();
        List<NFTMarketItem> nftMarketItems=new ArrayList<>();
        List<TypeReference<?>> outputParams=new ArrayList<>();
        outputParams.add(new TypeReference<DynamicArray<Address>>() {});
        List<Type>  types=TransactionFace.callContractViewMethod("0x3901952De2f16ad9B8646CF59C337d0b445A81Ca",registerContract,"getNFTList",list,outputParams);
        if (types!=null&&types.size()==1){
            Array hunterInfos= (Array)types.get(0);
            List<Address> ads=hunterInfos.getValue();
            ads.forEach(address -> {
                NFTMarketItem tokenInfo= getNFTCMarketDetail(registerContract,address.getValue());
                if (tokenInfo!=null&&tokenInfo.getUsed()){
                    nftMarketItems.add(tokenInfo);
                }
            });
        }
        return nftMarketItems;
    }


    /**
     * 获取nft图集详情
     * @return
     */
    public static NFTMarketItem getNFTCMarketDetail(String contract, String nftContractAddress){
        NFTMarketItem tokenInfo=new NFTMarketItem();
        List<Type> list=new ArrayList<>();
        List<TypeReference<?>> outputParams=new ArrayList<>();
        outputParams.add(new TypeReference<Utf8String>() {});
        outputParams.add(new TypeReference<Utf8String>() {});
        outputParams.add(new TypeReference<Utf8String>() {});
        outputParams.add(new TypeReference<Address>() {});
        outputParams.add(new TypeReference<Address>() {});
        outputParams.add(new TypeReference<Bool>() {});

        list.add(new Address(nftContractAddress));
        List<Type>  types=TransactionFace.callContractViewMethod("0x3901952De2f16ad9B8646CF59C337d0b445A81Ca",contract,"getNFTInfo",list,outputParams);
        if (types!=null&&types.size()==6){
            Utf8String img= (Utf8String)types.get(0);
            Utf8String title= (Utf8String)types.get(1);
            Utf8String content= (Utf8String)types.get(2);
            Address contractAddr= (Address)types.get(3);
            Address account= (Address)types.get(4);
            Bool used=(Bool)types.get(5);
            tokenInfo.setContent(content.getValue());
            tokenInfo.setTitle(title.getValue());
            tokenInfo.setImg(img.getValue());
            tokenInfo.setContract(contractAddr.getValue());
            tokenInfo.setCreator(account.getValue());
            tokenInfo.setUsed(used.getValue());
            return tokenInfo;
        }
        return null;
    }

    /**
     * 获取指定NFT物品详情
     * @param nftContract
     * @param nftTokenId
     * @return
     */
    public static NFTMarketItemDetail getNFTDetail(String nftContract,int nftTokenId){
        NFTMarketItemDetail tokenInfo=new NFTMarketItemDetail();
        List<Type> list=new ArrayList<>();
        List<TypeReference<?>> outputParams=new ArrayList<>();
        outputParams.add(new TypeReference<Uint256>() {});
        outputParams.add(new TypeReference<Address>() {});
        outputParams.add(new TypeReference<Bool>() {});
        outputParams.add(new TypeReference<Utf8String>() {});
        outputParams.add(new TypeReference<Utf8String>() {});
        outputParams.add(new TypeReference<Utf8String>() {});

        list.add(new Uint(new BigInteger(nftTokenId+"")));
        List<Type>  types=TransactionFace.callContractViewMethod("0x3901952De2f16ad9B8646CF59C337d0b445A81Ca",nftContract,"getTokenItemInfo",list,outputParams);
        if (types!=null&&types.size()==6){
            Uint256 price= (Uint256)types.get(0);
            Address account= (Address)types.get(1);
            Bool status=(Bool)types.get(2);
            Utf8String url= (Utf8String)types.get(3);
            Utf8String title= (Utf8String)types.get(4);
            Utf8String content= (Utf8String)types.get(5);

            tokenInfo.setAccount(account.getValue());
            tokenInfo.setCanTrade(status.getValue());
            tokenInfo.setContent(content.getValue());
            tokenInfo.setContract(nftContract);
            tokenInfo.setImg(url.getValue());
            tokenInfo.setTitle(title.getValue());
            tokenInfo.setTokenId(nftTokenId);
            tokenInfo.setPrice(price.getValue()+"");
            return tokenInfo;
        }
        return null;
    }

    public static int getNFTAmount(String nftContract){
        List<Type> list=new ArrayList<>();
        List<TypeReference<?>> outputParams=new ArrayList<>();
        outputParams.add(new TypeReference<Uint>() {});
        List<Type>  types=TransactionFace.callContractViewMethod("0x3901952De2f16ad9B8646CF59C337d0b445A81Ca",nftContract,"totalSupply",list,outputParams);
        if (types!=null&&types.size()==1){
            Uint count= (Uint)types.get(0);
            return count.getValue().intValue();
        }
        return 0;
    }


    /**
     * 铸造NFT
     */
    public static String mintNFT(String contract,String priKey,String toAddr,String title,String content,BigInteger price,String url ){
        List<Type> params= Arrays.asList(new Utf8String(url),new Address(toAddr),new Uint(price),new Utf8String(title),new Utf8String(content));
        return TransactionFace.callContractFunctionOp(priKey,contract,params,"mint", BaseMsg.GAS_LIMIT.toBigInteger(),BaseMsg.GAS_PRICE.toBigInteger());
    }

    /**
     * 转移NFT
     * address from, address to, uint256 tokenId
     */
    public static String transferFrom(String contract,String priKey,String toAddr,BigInteger tokenId ){
        String address = Crypto.generateAddressFromPriv(priKey);
        List<Type> params= Arrays.asList(new Address(address),new Address(toAddr),new Uint(tokenId));
        return TransactionFace.callContractFunctionOp(priKey,contract,params,"transferFrom", BaseMsg.GAS_LIMIT.toBigInteger(),BaseMsg.GAS_PRICE.toBigInteger());
    }





}
