package com.dechain.msg.gov;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.gson.annotations.SerializedName;
import com.dechain.msg.common.Message;
import com.dechain.msg.common.Token;
import com.dechain.msg.gov.MsgParameterChangeProposalWrapperValue;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
public class MsgSubmitProposalValue<T> {

    @JsonProperty("content")
    @SerializedName("content")
    private T content;

    @JsonProperty("initial_deposit")
    @SerializedName("initial_deposit")
    private List<Token> initialDeposit;

    @JsonProperty("proposer")
    @SerializedName("proposer")
    private String proposer;

    public void setContent(T content) {
        this.content = content;
    }

    public void setInitialDeposit(List<Token> initialDeposit) {
        this.initialDeposit = initialDeposit;
    }

    public void setProposer(String proposer) {
        this.proposer = proposer;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("content", content)
            .append("initial_deposit", initialDeposit)
            .append("proposer", proposer)
            .toString();
    }
}
