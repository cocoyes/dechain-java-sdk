package com.dechain.face;

import com.alibaba.fastjson.JSON;
import com.dechain.msg.coin.*;
import com.dechain.utils.ContractUtil;
import com.dechain.utils.HexUtil;
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
     * 获取某地址在某NFT合约下持有的NFT数量
     * @param nftContract
     * @param ownAddress
     * @return
     */
    public static int getNFTBalanceOf(String nftContract,String ownAddress){
        ownAddress=HexUtil.toHexAddr(ownAddress);
        List<Type> params= Arrays.asList(new Address(ownAddress));
        List<TypeReference<?>> outputParams=new ArrayList<>();
        outputParams.add(new TypeReference<Uint>() {});
        List<Type>  types=TransactionFace.callContractViewMethod("0x3901952De2f16ad9B8646CF59C337d0b445A81Ca",nftContract,"balanceOf",params,outputParams);
        if (types!=null&&types.size()==1){
            Uint count= (Uint)types.get(0);
            return count.getValue().intValue();
        }
        return 0;
    }


    /**
     * 铸造NFT
     */
    public static BaseMsg mintNFT(String contract,String priKey,String toAddr,String title,String content,BigInteger price,String url ){
        toAddr=HexUtil.toHexAddr(toAddr);
        List<Type> params= Arrays.asList(new Utf8String(url),new Address(toAddr),new Uint(price),new Utf8String(title),new Utf8String(content));
        return BaseFace.dealMsg(TransactionFace.callContractFunctionOp(priKey,contract,params,"mint", BaseMsg.GAS_LIMIT.toBigInteger(),BaseMsg.GAS_PRICE.toBigInteger()));
    }

    /**
     * 转移NFT
     * address from, address to, uint256 tokenId
     */
    public static BaseMsg transferFrom(String contract,String priKey,String toAddr,BigInteger tokenId ){
        String address = Crypto.generateAddressFromPriv(priKey);
        String hex=HexUtil.toHexAddr(address);
        List<Type> params= Arrays.asList(new Address(hex),new Address(toAddr),new Uint(tokenId));
        return BaseFace.dealMsg(TransactionFace.callContractFunctionOp(priKey,contract,params,"transferFrom", BaseMsg.GAS_LIMIT.toBigInteger(),BaseMsg.GAS_PRICE.toBigInteger()));
    }




    /**
     * 若要获取某用户在某个NFT图集下拥有的NFT单元，步骤较为繁琐，应当以切换模式或者流加载方式去展示给用户
     * 第一步：获取它在该NFT合约下拥有的NFT单元总数
     * 第二步：根据从【0~单元总数-1】索引读取到对应的tokenId
     * 第三步：根据tokenId获取该NFT单元的详情
     *
     * 对于用户层面： 列表层可展示拥有的图集与持有个数
     *              详情内再去挨个读取tokenId
     *              凭借tokenId 去获取详情
     *
     * step 1
     */
    public static List<MyNFTList> getAllNFTListByAddress(String registerContract,String address){
        List<MyNFTList> list=new ArrayList<>();
        List<NFTMarketItem>  parentList=getNFTMarketSimpleItem(registerContract);
        for(NFTMarketItem item:parentList){
            int count=getNFTBalanceOf(item.getContract(),address);
            if(count>0){
                MyNFTList myNFTList=new MyNFTList();
                myNFTList.setParent(item);
                myNFTList.setCount(count);
                list.add(myNFTList);
            }
        }
        return list;
    }


    /**
     * 根据索引获取对应的NFT单元详情
     * step 2 step 3
     */
    public static NFTMarketItemDetail getNFTDetail(String nftContract,String address,int index){
        List<Type> params= Arrays.asList(new Address(address),new Uint256(index));
        List<TypeReference<?>> outputParams=new ArrayList<>();
        outputParams.add(new TypeReference<Uint>() {});
        List<Type>  types=TransactionFace.callContractViewMethod("0x3901952De2f16ad9B8646CF59C337d0b445A81Ca",nftContract,"tokenOfOwnerByIndex",params,outputParams);
        if (types!=null&&types.size()==1){
            Uint count= (Uint)types.get(0);
            return getNFTDetail(nftContract,count.getValue().intValue());
        }else {
            return null;
        }
    }


}
