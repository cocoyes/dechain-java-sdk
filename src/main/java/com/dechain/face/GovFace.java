package com.dechain.face;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dechain.env.EnvInstance;
import com.dechain.msg.coin.Validators;
import com.dechain.utils.HttpUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class GovFace {

    //获取验证者列表
    public static List<Validators> getValidators(){
        //cosmos/staking/v1beta1/validators
        String result=HttpUtils.httpGet("http://"+EnvInstance.getEnv().getIpAddr()+":1317/cosmos/staking/v1beta1/validators");
        if(StringUtils.isNotEmpty(result)){
            JSONObject jsonObject=JSONObject.parseObject(result);
            JSONArray jsonArray=jsonObject.getJSONArray("validators");
            List<Validators> list=new ArrayList<>();
            for(int i=0;i<jsonArray.size();i++){
                JSONObject itemObject=jsonArray.getJSONObject(i);
                JSONObject descObj=itemObject.getJSONObject("description");
                String moniker=descObj.getString("moniker");
                String identity=descObj.getString("identity");
                String website=descObj.getString("website");
                String security_contact=descObj.getString("security_contact");
                String details=descObj.getString("details");
                String operator_address=itemObject.getString("operator_address");
                String status=itemObject.getString("status");
                String delegator_shares=itemObject.getString("delegator_shares");
                String tokens=itemObject.getString("tokens");
                String min_self_delegation=itemObject.getString("min_self_delegation");
                Validators validators=new Validators();
                validators.setAddress(operator_address);
                validators.setDelegatorShares(delegator_shares);
                validators.setDetails(details);
                validators.setIdentity(identity);
                validators.setMoniker(moniker);
                validators.setStatus(status);
                validators.setWebsite(website);
                validators.setSecurityContact(security_contact);
                validators.setToken(tokens);
                validators.setMinSelfDelegation(min_self_delegation);
                list.add(validators);
            }
            return list;
        }else {
            return new ArrayList<>();
        }
    }


    /**
     * 向指定验证者质押
     */


}
