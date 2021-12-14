package com.dechain.msg.token;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.gson.annotations.SerializedName;
import com.dechain.msg.common.Token;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
public class MsgTokenBurnValue {

    @JsonProperty("amount")
    @SerializedName("amount")
    private Token amount;

    @JsonProperty("owner")
    @SerializedName("owner")
    private String owner;

    public void setAmount(Token amount) {this.amount = amount;}

    public void setOwner(String owner) {this.owner = owner;}

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("amount", amount)
                .append("owner", owner)
                .toString();
    }

}
