package br.com.rcp.ordermanager.orders.exceptions;

public class OutOfStockException extends Exception {
    public OutOfStockException(String name, String item) {
        super("Cannot create order for user " + name + " because " + item + " quantity exceeds current stock!");
    }
}
