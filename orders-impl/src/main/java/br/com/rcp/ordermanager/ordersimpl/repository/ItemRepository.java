package br.com.rcp.ordermanager.ordersimpl.repository;

import br.com.rcp.ordermanager.orders.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}
