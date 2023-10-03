package br.com.rcp.ordermanager.ordersimpl.services;

import br.com.rcp.ordermanager.orders.models.Item;
import br.com.rcp.ordermanager.orders.services.ItemService;
import br.com.rcp.ordermanager.ordersimpl.repository.ItemRepository;
import br.com.rcp.ordermanager.ordersimpl.services.base.AbstractServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl extends AbstractServiceImpl<Item, ItemRepository> implements ItemService {

    public ItemServiceImpl(ItemRepository repository) {
        super(repository);
    }

}
