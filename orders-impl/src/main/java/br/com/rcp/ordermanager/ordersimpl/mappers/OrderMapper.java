package br.com.rcp.ordermanager.ordersimpl.mappers;

import br.com.rcp.ordermanager.orders.mappers.IMapper;
import br.com.rcp.ordermanager.orders.models.Order;
import br.com.rcp.ordermanager.ordersimpl.dto.OrderDTO;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper implements IMapper<Order, OrderDTO> {
    @Override
    public OrderDTO toDTO(Order entity) {
        OrderDTO data = new OrderDTO();

        data.setIdentifier(entity.getIdentifier());
        data.setItem(entity.getItem().getIdentifier());
        data.setUser(entity.getUser().getIdentifier());
        data.setDone(entity.isDone());
        data.setQuantity(entity.getQuantity());
        data.setCreatedAt(entity.getCreatedAt());

        return data;
    }

    @Override
    public Order fromDTO(OrderDTO data) {
        Order entity = new Order();

        entity.setIdentifier(data.getIdentifier());
        entity.setQuantity(data.getQuantity());

        return entity;
    }
}
