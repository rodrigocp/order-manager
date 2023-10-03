package br.com.rcp.ordermanager.ordersimpl.dto;

import br.com.rcp.ordermanager.orders.dto.IDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
public class ItemDTO implements IDTO {

    @JsonProperty("id")
    private Long identifier;

    @JsonProperty("name")
    private String name;

    @Override
    public Long getIdentifier() {
        return identifier;
    }

    @Override
    public void setIdentifier(Long identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
