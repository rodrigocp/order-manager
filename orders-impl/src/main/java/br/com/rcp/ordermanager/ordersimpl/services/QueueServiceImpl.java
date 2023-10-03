package br.com.rcp.ordermanager.ordersimpl.services;

import br.com.rcp.ordermanager.orders.exceptions.EntityNotFoundException;
import br.com.rcp.ordermanager.orders.models.Item;
import br.com.rcp.ordermanager.orders.models.Order;
import br.com.rcp.ordermanager.orders.models.StockMovement;
import br.com.rcp.ordermanager.orders.models.User;
import br.com.rcp.ordermanager.orders.services.ItemService;
import br.com.rcp.ordermanager.orders.services.OrderService;
import br.com.rcp.ordermanager.orders.services.StockMovementService;
import br.com.rcp.ordermanager.orders.services.UserService;
import br.com.rcp.ordermanager.ordersimpl.services.base.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_SINGLETON;

@Service
@Scope(SCOPE_SINGLETON)
public class QueueServiceImpl implements QueueService {
    private final Queue<Order> queue;

    private final UserService userService;

    private final ItemService itemService;

    private final OrderService orderService;

    private final StockMovementService stockMovementService;

    @Autowired
    public QueueServiceImpl(
        UserService userService,
        ItemService itemService,
        OrderService orderService,
        StockMovementService stockMovementService
    ) {
        this.queue = new ConcurrentLinkedQueue<>();
        this.userService = userService;
        this.itemService = itemService;
        this.orderService = orderService;
        this.stockMovementService = stockMovementService;
        fillQueue();
    }

    @Transactional
    @Override
    public Order create(Order entity, Long itemID, Long userID) throws EntityNotFoundException {
        Item item = itemService.find(itemID);
        User user = userService.find(userID);
        StockMovement movement = stockMovementService.create(item, entity.getQuantity());
        boolean isValidStockMovement = movement != null;

        entity.setItem(item);
        entity.setUser(user);
        entity.setDone(isValidStockMovement);

        if (!isValidStockMovement) {
            queue.add(entity);
        }

        return orderService.create(entity);

    }

    @Transactional
    @Override
    public Order update(Order entity, Long itemID, Long userID) throws EntityNotFoundException {
        Order order = orderService.find(entity.getIdentifier());

        if (order.isDone()) {
            return order;
        } else {
            Item item = itemService.find(itemID);
            User user = userService.find(userID);
            StockMovement movement = stockMovementService.create(item, entity.getQuantity());
            boolean isValidMovement = movement != null;

            entity.setItem(item);
            entity.setUser(user);
            entity.setDone(isValidMovement);

            if (!isValidMovement) {
                queue.add(entity);
            }

            return orderService.update(entity);
        }
    }

    @Transactional
    public void notifyStockMovementUpdated(StockMovement movement) throws EntityNotFoundException {
        Item item = movement.getItem();
        Order order = queue.stream().filter(i ->
            Objects.equals(i.getItem().getIdentifier(), item.getIdentifier())
        ).findAny().orElse(null);

        if (order != null) {
            User user = order.getUser();
            int stockQuantity = stockMovementService.getStockQuantity(movement.getItem().getIdentifier());

            if (order.getQuantity() <= stockQuantity) {
                Order updated = update(order, item.getIdentifier(), user.getIdentifier());

                if (updated.isDone()) {
                    queue.removeIf(i ->
                        Objects.equals(i.getIdentifier(), updated.getIdentifier())
                    );

                    // TODO: Send email;
                }
            }
        }
    }

    private void fillQueue() {
        queue.addAll(orderService.findAllPendingOrders());
    }
}
