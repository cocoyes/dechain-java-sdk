package com.dechain.msg.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
public class MsgNewOrdersValue {

    @JsonProperty("order_items")
    @SerializedName("order_items")
    private List<OrderItem> orderItems;

    @JsonProperty("sender")
    @SerializedName("sender")
    private String sender;

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setNewOrderItem(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("sender", sender)
                .append("order_items", orderItems)
                .toString();
    }
}
