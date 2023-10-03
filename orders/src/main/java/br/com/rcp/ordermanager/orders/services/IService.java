package br.com.rcp.ordermanager.orders.services;

import br.com.rcp.ordermanager.orders.exceptions.EntityNotFoundException;
import br.com.rcp.ordermanager.orders.models.IEntity;

import java.util.List;

public interface IService<T extends IEntity> {
    /**
     * Creates a new object instance on database
     * @param entity entity to be persisted on database
     * @return updated entity persisted on database
     */
    T create(T entity);

    /**
     * Find all entities of given type
     * @return list of all entities of given type on database
     */
    List<T> find();

    /**
     * Find unique entity by its identifier
     * @param identifier the database identifier
     * @return entity that matches with provided identifier
     */
    T find(long identifier) throws EntityNotFoundException;

    /**
     * Update entity data.
     * @param entity entity to be updated with existing identifier
     * @return the updated entity data
     */
    T update(T entity) throws EntityNotFoundException;

    /**
     * Deletes entity from database
     * @param identifier the entity identifier
     */
    void delete(long identifier) throws EntityNotFoundException;
}
