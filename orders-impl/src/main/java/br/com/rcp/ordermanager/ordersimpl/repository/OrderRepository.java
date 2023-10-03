package br.com.rcp.ordermanager.ordersimpl.repository;

import br.com.rcp.ordermanager.orders.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByDoneIsFalseOrderByCreatedAt();
}
