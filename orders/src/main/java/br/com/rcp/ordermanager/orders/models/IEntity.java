package br.com.rcp.ordermanager.orders.models;

/**
 * Define type of all entities.
 */
public interface IEntity {
    /**
     * Get entity identifier
     * @return entity identifier as nullable long object
     */
    Long getIdentifier();

    /**
     * Set entity identifier
     * @param identifier the entity identifier
     */
    void setIdentifier(Long identifier);
}
