package br.com.rcp.ordermanager.ordersimpl.services.base;

import br.com.rcp.ordermanager.orders.exceptions.EntityNotFoundException;
import br.com.rcp.ordermanager.orders.models.IEntity;
import br.com.rcp.ordermanager.orders.models.Order;
import br.com.rcp.ordermanager.orders.models.StockMovement;

public interface QueueService {

    Order create(Order entity, Long itemID, Long userID) throws EntityNotFoundException;

    Order update(Order entity, Long itemID, Long userID) throws EntityNotFoundException;

    void notifyStockMovementUpdated(StockMovement movement) throws EntityNotFoundException;
}
