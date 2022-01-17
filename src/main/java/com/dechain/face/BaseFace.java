package com.dechain.face;

import com.dechain.msg.coin.BaseMsg;
import com.dechain.msg.coin.RegisterTokenDto;
import com.dechain.utils.ContractUtil;
import org.apache.commons.lang3.StringUtils;
import org.web3j.crypto.Credentials;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;

public class BaseFace {

    public static BaseMsg dealMsg(String hash){
        BaseMsg baseMsg=new BaseMsg();
        baseMsg.setHash(hash);
        try {
            CompletableFuture<String> futureSubmit = CompletableFuture.supplyAsync(()->{
                return hash;
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
            baseMsg.setHash(hash);
            if (future2.join()){
                baseMsg.setMsg("success");
                baseMsg.setSuccess(true);
            }else {
                baseMsg.setMsg("失败");
                baseMsg.setSuccess(false);
            }
            return baseMsg;
        }catch (Exception e){
            e.printStackTrace();
            return BaseMsg.buildError("执行错误");
        }
    }
}
