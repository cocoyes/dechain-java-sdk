package com.dechain.msg.dex;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
public class MsgUpdateOperatorValue {
    @JsonProperty("handling_fee_address")
    @SerializedName("handling_fee_address")
    private String handlingFeeAddress;

    @JsonProperty("owner")
    @SerializedName("owner")
    private String owner;

    @JsonProperty("website")
    @SerializedName("website")
    private String website;

    public void setHandlingFeeAddress(String handlingFeeAddress) {
        this.handlingFeeAddress = handlingFeeAddress;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setWebsite(String website) { this.website = website; }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("handling_fee_address", handlingFeeAddress)
                .append("owner", owner)
                .append("website", website)
                .toString();
    }
}
