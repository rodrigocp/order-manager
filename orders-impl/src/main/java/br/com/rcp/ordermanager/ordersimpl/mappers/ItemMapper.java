package br.com.rcp.ordermanager.ordersimpl.mappers;

import br.com.rcp.ordermanager.orders.mappers.IMapper;
import br.com.rcp.ordermanager.orders.models.Item;
import br.com.rcp.ordermanager.ordersimpl.dto.ItemDTO;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper implements IMapper<Item, ItemDTO> {
    @Override
    public ItemDTO toDTO(Item entity) {
        ItemDTO data = new ItemDTO();

        data.setIdentifier(entity.getIdentifier());
        data.setName(entity.getName());

        return data;
    }

    @Override
    public Item fromDTO(ItemDTO data) {
        Item entity = new Item();

        entity.setIdentifier(data.getIdentifier());
        entity.setName(data.getName());

        return entity;
    }
}
