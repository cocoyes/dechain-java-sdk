package com.dechain.msg.coin;

public class MyNFTList   {
    private NFTMarketItem parent;
    private Integer count;

    public NFTMarketItem getParent() {
        return parent;
    }

    public void setParent(NFTMarketItem parent) {
        this.parent = parent;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
