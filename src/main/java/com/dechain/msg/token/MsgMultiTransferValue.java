package com.dechain.msg.token;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.gson.annotations.SerializedName;
import com.dechain.msg.common.TransferUnit;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
public class MsgMultiTransferValue {

    @JsonProperty("from")
    @SerializedName("from")
    private String from;

    @JsonProperty("transfers")
    @SerializedName("transfers")
    private List<TransferUnit> transfers;

    public List<TransferUnit> getTransfers() {
        return transfers;
    }

    public void setTransfers(List<TransferUnit> transferUnits) { this.transfers = transferUnits; }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("from", from)
                .append("transfers", transfers)
                .toString();
    }
}
