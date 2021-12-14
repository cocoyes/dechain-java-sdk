package com.dechain.msg.staking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.gson.annotations.SerializedName;
import com.dechain.msg.common.Description;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
public class MsgEditValidatorValue {

    @JsonProperty("Description")
    @SerializedName("Description")
    private Description description;

    @JsonProperty("address")
    @SerializedName("address")
    private String address;

    public void setDescription(Description description) {
        this.description = description;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("address", address)
                .append("Description", this.description)
                .toString();
    }
}
