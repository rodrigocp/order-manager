package br.com.rcp.ordermanager.orders.exceptions;

public class EntityNotFoundException extends Exception {
    public EntityNotFoundException(Long identifier) {
        super("Entity with identifier " + identifier + " not found!");
    }
}
