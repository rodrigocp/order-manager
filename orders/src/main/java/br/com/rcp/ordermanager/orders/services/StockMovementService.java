package br.com.rcp.ordermanager.orders.services;

import br.com.rcp.ordermanager.orders.exceptions.EntityNotFoundException;
import br.com.rcp.ordermanager.orders.models.Item;
import br.com.rcp.ordermanager.orders.models.StockMovement;

public interface StockMovementService extends IService<StockMovement> {
    StockMovement create(StockMovement entity, Long itemID) throws EntityNotFoundException;

    StockMovement update(StockMovement entity, Long itemID) throws EntityNotFoundException;

    StockMovement create(Item item, int quantity);

    int getStockQuantity(long itemID);
}
