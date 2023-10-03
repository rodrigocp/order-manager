package br.com.rcp.ordermanager.orders.dto;

/**
 * Base DTO class
 * As it is a simple exercise application database identifier is exposed.
 * In real applications it is a bad pratice.
 */
public interface IDTO {
    Long getIdentifier();

    void setIdentifier(Long identifier);
}
