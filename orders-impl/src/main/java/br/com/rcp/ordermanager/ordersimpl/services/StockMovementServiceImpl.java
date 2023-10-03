package br.com.rcp.ordermanager.ordersimpl.services;

import br.com.rcp.ordermanager.orders.exceptions.EntityNotFoundException;
import br.com.rcp.ordermanager.orders.models.Item;
import br.com.rcp.ordermanager.orders.models.StockMovement;
import br.com.rcp.ordermanager.orders.services.StockMovementService;
import br.com.rcp.ordermanager.ordersimpl.repository.ItemRepository;
import br.com.rcp.ordermanager.ordersimpl.repository.StockMovementRepository;
import br.com.rcp.ordermanager.ordersimpl.services.base.AbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockMovementServiceImpl extends AbstractServiceImpl<StockMovement, StockMovementRepository> implements StockMovementService {
    private final ItemRepository itemRepository;

    @Autowired
    public StockMovementServiceImpl(StockMovementRepository repository, ItemRepository itemRepository) {
        super(repository);
        this.itemRepository = itemRepository;
    }

    @Override
    public StockMovement create(StockMovement entity, Long itemID) throws EntityNotFoundException {
        Item item = itemRepository.findById(itemID).orElseThrow(() -> new EntityNotFoundException(itemID));
        entity.setItem(item);
        return create(entity);
    }

    @Override
    public StockMovement update(StockMovement entity, Long itemID) throws EntityNotFoundException {
        Item item = itemRepository.findById(itemID).orElseThrow(() -> new EntityNotFoundException(itemID));
        entity.setItem(item);
        return update(entity);
    }

    @Override
    public StockMovement create(Item item, int quantity) {
        StockMovement movement = new StockMovement();

        if (quantity > getStockQuantity(item.getIdentifier())) {
            return null;
        }

        movement.setItem(item);
        movement.setQuantity(quantity * (-1));

        return create(movement);
    }

    @Override
    public int getStockQuantity(long itemID) {
        return getRepository().sumQuantityByItemId(itemID);
    }
}
