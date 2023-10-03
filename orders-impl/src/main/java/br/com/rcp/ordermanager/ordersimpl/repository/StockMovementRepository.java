package br.com.rcp.ordermanager.ordersimpl.repository;

import br.com.rcp.ordermanager.orders.models.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {

    @Query("select sum(stock.quantity) from StockMovement stock where stock.item.identifier = :identifier")
    int sumQuantityByItemId(long identifier);

}
