package com.dechain.msg.coin;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 公司公告对象 company_notice
 *
 * @author ruoyi
 * @date 2021-09-28
 */
public class CompanyNotice
{
    /** 标题 */
    private String title;

    /** 公告类型 */
    private String noticeType;

    /** 内容 */
    private String content;

    /** 公司名称 */
    private String companyName;

    private String createTime;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }
    public void setNoticeType(String noticeType)
    {
        this.noticeType = noticeType;
    }

    public String getNoticeType()
    {
        return noticeType;
    }
    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public String getCompanyName()
    {
        return companyName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("title", getTitle())
            .append("noticeType", getNoticeType())
            .append("createTime", getCreateTime())
            .append("content", getContent())
            .append("companyName", getCompanyName())
            .toString();
    }
}
