package br.com.rcp.ordermanager.orders.mappers;

import br.com.rcp.ordermanager.orders.dto.IDTO;
import br.com.rcp.ordermanager.orders.models.IEntity;

public interface IMapper <E extends IEntity, DTO extends IDTO> {
    /**
     * Map an entity to a DTO
     * @param entity the entity from database
     * @return a data transfer object
     */
    DTO toDTO(E entity);

    /**
     * Map a DTO to an entity
     * @param data the data transfer object
     * @return an entity
     */
    E fromDTO(DTO data);
}
