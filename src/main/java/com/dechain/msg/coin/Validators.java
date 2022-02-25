package com.dechain.msg.coin;

public class Validators {
    private String address;
    private String moniker; //验证者别名
    private String identity; //身份
    private String website; //网站
    private String securityContact;
    private String details;
    private String token;
    private String delegatorShares; //验证者股份份额
    private String minSelfDelegation; //最小投入份额

    /**
     * BOND_STATUS_BONDED 已激活，正常运行中
     * 其它都是未激活或处于离线被惩罚中
     */
    private String status;

    public String getMinSelfDelegation() {
        return minSelfDelegation;
    }

    public void setMinSelfDelegation(String minSelfDelegation) {
        this.minSelfDelegation = minSelfDelegation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMoniker() {
        return moniker;
    }

    public void setMoniker(String moniker) {
        this.moniker = moniker;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getSecurityContact() {
        return securityContact;
    }

    public void setSecurityContact(String securityContact) {
        this.securityContact = securityContact;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDelegatorShares() {
        return delegatorShares;
    }

    public void setDelegatorShares(String delegatorShares) {
        this.delegatorShares = delegatorShares;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
