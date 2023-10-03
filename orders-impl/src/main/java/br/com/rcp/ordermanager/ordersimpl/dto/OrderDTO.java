package br.com.rcp.ordermanager.ordersimpl.dto;

import br.com.rcp.ordermanager.orders.dto.IDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
public class OrderDTO implements IDTO {

    @JsonProperty("id")
    private Long identifier;

    @JsonProperty("item_id")
    private Long item;

    @JsonProperty("user_id")
    private Long user;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("done")
    private boolean done;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @Override
    public Long getIdentifier() {
        return identifier;
    }

    @Override
    public void setIdentifier(Long identifier) {
        this.identifier = identifier;
    }

    public Long getItem() {
        return item;
    }

    public void setItem(Long item) {
        this.item = item;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
