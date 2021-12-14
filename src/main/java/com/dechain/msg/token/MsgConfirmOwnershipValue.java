package com.dechain.msg.token;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
public class MsgConfirmOwnershipValue{

    @JsonProperty("new_owner")
    @SerializedName("new_owner")
    private String address;

    @JsonProperty("symbol")
    @SerializedName("symbol")
    private String symbol;

    public void setAddress(String address) {this.address = address;}

    public void setSymbol(String symbol) {this.symbol = symbol;}

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("new_owner", address)
                .append("symbol", symbol)
                .toString();
    }
}
