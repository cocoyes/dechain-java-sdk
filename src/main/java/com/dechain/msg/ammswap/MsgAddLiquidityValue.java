package com.dechain.msg.ammswap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.gson.annotations.SerializedName;
import com.dechain.msg.common.Token;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
public class MsgAddLiquidityValue {

    @JsonProperty("deadline")
    @SerializedName("deadline")
    private String deadline;

    @JsonProperty("max_base_amount")
    @SerializedName("max_base_amount")
    private Token maxBaseAmount;

    @JsonProperty("min_liquidity")
    @SerializedName("min_liquidity")
    private String minLiquidity;

    @JsonProperty("quote_amount")
    @SerializedName("quote_amount")
    private Token quoteAmount;

    @JsonProperty("sender")
    @SerializedName("sender")
    private String sender;

    public void setDeadline(String deadline) { this.deadline = deadline; }

    public void setMinLiquidity(String minLiquidity) {
        this.minLiquidity = minLiquidity;
    }

    public void setMaxBaseAmount(Token maxBaseAmount) {
        this.maxBaseAmount = maxBaseAmount;
    }

    public void setQuoteAmount(Token quoteAmount) { this.quoteAmount = quoteAmount; }

    public void setSender(String sender) {
        this.sender = sender;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("min_liquidity", minLiquidity)
                .append("max_base_amount", maxBaseAmount)
                .append("quote_amount", quoteAmount)
                .append("deadline", deadline)
                .append("sender", sender)
                .toString();
    }
}
