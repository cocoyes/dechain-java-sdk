package com.dechain.msg.coin;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 公司报告对象 company_report
 */
public class CompanyReport {
    /**
     * 营业收入
     */
    private BigDecimal yingyeShouru;

    /**
     * 营业成本
     */
    private BigDecimal yingyeChengben;

    /**
     * 营业利润
     */
    private BigDecimal yingyeLirun;

    /**
     * 税费
     */
    private BigDecimal fee;

    /**
     * 净利润
     */
    private BigDecimal jingLirun;

    /**
     * 每股收益
     */
    private BigDecimal profit;

    /**
     * 货币资金
     */
    private BigDecimal fuzhaiMoney;

    /**
     * 应收账款
     */
    private BigDecimal fuzhaiYingshou;

    /**
     * 存货
     */
    private BigDecimal fuzhaiCunhuo;

    /**
     * 流动资金
     */
    private BigDecimal fuzhaiFlowMoney;

    /**
     * 流动负债合计
     */
    private BigDecimal fuzhaiFlowTotal;

    /**
     * 非流动负债合计
     */
    private BigDecimal fuzhaiFeiFlowTotal;

    /**
     * 负债合计
     */
    private BigDecimal fuzhaiTotal;

    /**
     * 股东权益合计
     */
    private BigDecimal power;

    /**
     * 现金或等价物余额
     */
    private BigDecimal flowCash;

    /**
     * 经验活动现金流
     */
    private BigDecimal flowJingying;

    /**
     * 投资活动现金流
     */
    private BigDecimal flowTouzi;

    /**
     * 筹资活动现金流
     */
    private BigDecimal flowChouzi;

    /**
     * 现金及等价物净增加额
     */
    private BigDecimal flowCashAdd;

    /**
     * 期末现金及等价物余额
     */
    private BigDecimal flowEnd;

    /**
     * 期次
     */
    private String timePeriod;

    /**
     * 公司编号
     */
    private Long companyId;

    /**
     * 公司名称
     */
    private String companyName;

    public void setYingyeShouru(BigDecimal yingyeShouru) {
        this.yingyeShouru = yingyeShouru;
    }

    public BigDecimal getYingyeShouru() {
        return yingyeShouru;
    }

    public void setYingyeChengben(BigDecimal yingyeChengben) {
        this.yingyeChengben = yingyeChengben;
    }

    public BigDecimal getYingyeChengben() {
        return yingyeChengben;
    }

    public void setYingyeLirun(BigDecimal yingyeLirun) {
        this.yingyeLirun = yingyeLirun;
    }

    public BigDecimal getYingyeLirun() {
        return yingyeLirun;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setJingLirun(BigDecimal jingLirun) {
        this.jingLirun = jingLirun;
    }

    public BigDecimal getJingLirun() {
        return jingLirun;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setFuzhaiMoney(BigDecimal fuzhaiMoney) {
        this.fuzhaiMoney = fuzhaiMoney;
    }

    public BigDecimal getFuzhaiMoney() {
        return fuzhaiMoney;
    }

    public void setFuzhaiYingshou(BigDecimal fuzhaiYingshou) {
        this.fuzhaiYingshou = fuzhaiYingshou;
    }

    public BigDecimal getFuzhaiYingshou() {
        return fuzhaiYingshou;
    }

    public void setFuzhaiCunhuo(BigDecimal fuzhaiCunhuo) {
        this.fuzhaiCunhuo = fuzhaiCunhuo;
    }

    public BigDecimal getFuzhaiCunhuo() {
        return fuzhaiCunhuo;
    }

    public void setFuzhaiFlowMoney(BigDecimal fuzhaiFlowMoney) {
        this.fuzhaiFlowMoney = fuzhaiFlowMoney;
    }

    public BigDecimal getFuzhaiFlowMoney() {
        return fuzhaiFlowMoney;
    }

    public void setFuzhaiFlowTotal(BigDecimal fuzhaiFlowTotal) {
        this.fuzhaiFlowTotal = fuzhaiFlowTotal;
    }

    public BigDecimal getFuzhaiFlowTotal() {
        return fuzhaiFlowTotal;
    }

    public void setFuzhaiFeiFlowTotal(BigDecimal fuzhaiFeiFlowTotal) {
        this.fuzhaiFeiFlowTotal = fuzhaiFeiFlowTotal;
    }

    public BigDecimal getFuzhaiFeiFlowTotal() {
        return fuzhaiFeiFlowTotal;
    }

    public void setFuzhaiTotal(BigDecimal fuzhaiTotal) {
        this.fuzhaiTotal = fuzhaiTotal;
    }

    public BigDecimal getFuzhaiTotal() {
        return fuzhaiTotal;
    }

    public void setPower(BigDecimal power) {
        this.power = power;
    }

    public BigDecimal getPower() {
        return power;
    }

    public void setFlowCash(BigDecimal flowCash) {
        this.flowCash = flowCash;
    }

    public BigDecimal getFlowCash() {
        return flowCash;
    }

    public void setFlowJingying(BigDecimal flowJingying) {
        this.flowJingying = flowJingying;
    }

    public BigDecimal getFlowJingying() {
        return flowJingying;
    }

    public void setFlowTouzi(BigDecimal flowTouzi) {
        this.flowTouzi = flowTouzi;
    }

    public BigDecimal getFlowTouzi() {
        return flowTouzi;
    }

    public void setFlowChouzi(BigDecimal flowChouzi) {
        this.flowChouzi = flowChouzi;
    }

    public BigDecimal getFlowChouzi() {
        return flowChouzi;
    }

    public void setFlowCashAdd(BigDecimal flowCashAdd) {
        this.flowCashAdd = flowCashAdd;
    }

    public BigDecimal getFlowCashAdd() {
        return flowCashAdd;
    }

    public void setFlowEnd(BigDecimal flowEnd) {
        this.flowEnd = flowEnd;
    }

    public BigDecimal getFlowEnd() {
        return flowEnd;
    }

    public void setTimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
    }

    public String getTimePeriod() {
        return timePeriod;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("yingyeShouru", getYingyeShouru())
                .append("yingyeChengben", getYingyeChengben())
                .append("yingyeLirun", getYingyeLirun())
                .append("fee", getFee())
                .append("jingLirun", getJingLirun())
                .append("profit", getProfit())
                .append("fuzhaiMoney", getFuzhaiMoney())
                .append("fuzhaiYingshou", getFuzhaiYingshou())
                .append("fuzhaiCunhuo", getFuzhaiCunhuo())
                .append("fuzhaiFlowMoney", getFuzhaiFlowMoney())
                .append("fuzhaiFlowTotal", getFuzhaiFlowTotal())
                .append("fuzhaiFeiFlowTotal", getFuzhaiFeiFlowTotal())
                .append("fuzhaiTotal", getFuzhaiTotal())
                .append("power", getPower())
                .append("flowCash", getFlowCash())
                .append("flowJingying", getFlowJingying())
                .append("flowTouzi", getFlowTouzi())
                .append("flowChouzi", getFlowChouzi())
                .append("flowCashAdd", getFlowCashAdd())
                .append("flowEnd", getFlowEnd())
                .append("timePeriod", getTimePeriod())
                .append("companyId", getCompanyId())
                .append("companyName", getCompanyName())
                .toString();
    }
}
