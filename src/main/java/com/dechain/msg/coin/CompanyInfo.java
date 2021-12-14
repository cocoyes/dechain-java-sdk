package com.dechain.msg.coin;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 公司基本信息对象 company_info
 *
 * @author ruoyi
 * @date 2021-09-28
 */
public class CompanyInfo
{
    private static final long serialVersionUID = 1L;


    /** 公司名称 */

    private String companyName;

    /** 公司简称 */

    private String companyJiancheng;

    /** 英文全称 */

    private String englishName;

    /** 注册资金 */

    private String registerMoney;

    /** 公司地址 */

    private String address;

    /** 联系电话 */

    private String phone;

    /** 邮箱 */

    private String email;

    /** 法人代表 */

    private String faRen;

    /** 总经理 */

    private String zongjingli;

    /** 主营业务 */

    private String zhuyingYewu;

    /** 经营范围 */

    private String jingyingFanwei;

    /** 注册时间 */

    private String registerTime;


    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public String getCompanyName()
    {
        return companyName;
    }
    public void setCompanyJiancheng(String companyJiancheng)
    {
        this.companyJiancheng = companyJiancheng;
    }

    public String getCompanyJiancheng()
    {
        return companyJiancheng;
    }
    public void setEnglishName(String englishName)
    {
        this.englishName = englishName;
    }

    public String getEnglishName()
    {
        return englishName;
    }
    public void setRegisterMoney(String registerMoney)
    {
        this.registerMoney = registerMoney;
    }

    public String getRegisterMoney()
    {
        return registerMoney;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getAddress()
    {
        return address;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getPhone()
    {
        return phone;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getEmail()
    {
        return email;
    }
    public void setFaRen(String faRen)
    {
        this.faRen = faRen;
    }

    public String getFaRen()
    {
        return faRen;
    }
    public void setZongjingli(String zongjingli)
    {
        this.zongjingli = zongjingli;
    }

    public String getZongjingli()
    {
        return zongjingli;
    }
    public void setZhuyingYewu(String zhuyingYewu)
    {
        this.zhuyingYewu = zhuyingYewu;
    }

    public String getZhuyingYewu()
    {
        return zhuyingYewu;
    }
    public void setJingyingFanwei(String jingyingFanwei)
    {
        this.jingyingFanwei = jingyingFanwei;
    }

    public String getJingyingFanwei()
    {
        return jingyingFanwei;
    }
    public void setRegisterTime(String registerTime)
    {
        this.registerTime = registerTime;
    }

    public String getRegisterTime()
    {
        return registerTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("companyName", getCompanyName())
            .append("companyJiancheng", getCompanyJiancheng())
            .append("englishName", getEnglishName())
            .append("registerMoney", getRegisterMoney())
            .append("address", getAddress())
            .append("phone", getPhone())
            .append("email", getEmail())
            .append("faRen", getFaRen())
            .append("zongjingli", getZongjingli())
            .append("zhuyingYewu", getZhuyingYewu())
            .append("jingyingFanwei", getJingyingFanwei())
            .append("registerTime", getRegisterTime())
            .toString();
    }
}
