package br.com.rcp.ordermanager.ordersimpl.services;

import br.com.rcp.ordermanager.orders.exceptions.EntityNotFoundException;
import br.com.rcp.ordermanager.orders.models.Item;
import br.com.rcp.ordermanager.orders.models.Order;
import br.com.rcp.ordermanager.orders.models.User;
import br.com.rcp.ordermanager.orders.services.OrderService;
import br.com.rcp.ordermanager.ordersimpl.repository.ItemRepository;
import br.com.rcp.ordermanager.ordersimpl.repository.OrderRepository;
import br.com.rcp.ordermanager.ordersimpl.repository.UserRepository;
import br.com.rcp.ordermanager.ordersimpl.services.base.AbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrderServiceImpl extends AbstractServiceImpl<Order, OrderRepository> implements OrderService {

    private final ItemRepository itemRepository;

    private final UserRepository userRepository;


    @Autowired
    public OrderServiceImpl(
        OrderRepository repository,
        ItemRepository itemRepository,
        UserRepository userRepository
    ) {
        super(repository);
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Order create(Order entity, Long itemID, Long userID) throws EntityNotFoundException {
        Item item = itemRepository.findById(itemID).orElseThrow(() -> new EntityNotFoundException(itemID));
        User user = userRepository.findById(userID).orElseThrow(() -> new EntityNotFoundException(userID));
        entity.setItem(item);
        entity.setUser(user);
        return create(entity);
    }

    @Override
    public Order update(Order entity, Long itemID, Long userID) throws EntityNotFoundException {
        Item item = itemRepository.findById(itemID).orElseThrow(() -> new EntityNotFoundException(itemID));
        User user = userRepository.findById(userID).orElseThrow(() -> new EntityNotFoundException(userID));
        entity.setItem(item);
        entity.setUser(user);
        return update(entity);
    }

    @Override
    public List<Order> findAllPendingOrders() {
        return getRepository().findAllByDoneIsFalseOrderByCreatedAt();
    }
}
