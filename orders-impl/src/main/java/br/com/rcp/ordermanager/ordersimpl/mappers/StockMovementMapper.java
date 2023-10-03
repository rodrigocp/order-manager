package br.com.rcp.ordermanager.ordersimpl.mappers;

import br.com.rcp.ordermanager.orders.mappers.IMapper;
import br.com.rcp.ordermanager.orders.models.StockMovement;
import br.com.rcp.ordermanager.ordersimpl.dto.StockMovementDTO;
import org.springframework.stereotype.Component;

@Component
public class StockMovementMapper implements IMapper<StockMovement, StockMovementDTO> {
    @Override
    public StockMovementDTO toDTO(StockMovement entity) {
        StockMovementDTO data = new StockMovementDTO();

        data.setIdentifier(entity.getIdentifier());
        data.setQuantity(entity.getQuantity());
        data.setCreatedAt(entity.getCreatedAt());
        data.setItem(entity.getItem().getIdentifier());

        return data;
    }

    @Override
    public StockMovement fromDTO(StockMovementDTO data) {
        StockMovement entity = new StockMovement();

        entity.setIdentifier(data.getIdentifier());
        entity.setQuantity(data.getQuantity());

        return entity;
    }
}
