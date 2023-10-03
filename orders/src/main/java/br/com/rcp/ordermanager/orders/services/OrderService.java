package br.com.rcp.ordermanager.orders.services;

import br.com.rcp.ordermanager.orders.exceptions.EntityNotFoundException;
import br.com.rcp.ordermanager.orders.models.Order;

import java.util.List;

public interface OrderService extends IService<Order> {

    Order create(Order entity, Long itemID, Long userID) throws EntityNotFoundException;

    Order update(Order entity, Long itemID, Long userID) throws EntityNotFoundException;

    List<Order> findAllPendingOrders();
}
