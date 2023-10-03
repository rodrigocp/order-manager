package br.com.rcp.ordermanager.ordersimpl.controllers;

import br.com.rcp.ordermanager.orders.controllers.IController;
import br.com.rcp.ordermanager.orders.exceptions.EntityNotFoundException;
import br.com.rcp.ordermanager.orders.models.Order;
import br.com.rcp.ordermanager.orders.services.OrderService;
import br.com.rcp.ordermanager.ordersimpl.dto.OrderDTO;
import br.com.rcp.ordermanager.ordersimpl.mappers.OrderMapper;
import br.com.rcp.ordermanager.ordersimpl.services.base.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController implements IController<OrderDTO> {

    private final OrderService service;

    private final QueueService queueService;

    private final OrderMapper mapper;

    @Autowired
    public OrderController(OrderService service, QueueService queueService, OrderMapper mapper) {
        this.queueService = queueService;
        this.service = service;
        this.mapper = mapper;

    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    @Override
    public ResponseEntity<OrderDTO> create(@RequestBody OrderDTO body) throws EntityNotFoundException {
        Order mapped = mapper.fromDTO(body);
        Order result = queueService.create(mapped, body.getItem(), body.getUser());
        OrderDTO dto = mapper.toDTO(result);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(produces = "application/json")
    @Override
    public ResponseEntity<List<OrderDTO>> find() {
        List<Order> fetched = service.find();
        List<OrderDTO> mapped = fetched.stream().map(mapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(mapped);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @Override
    public ResponseEntity<OrderDTO> find(@PathVariable("id") long identifier) throws EntityNotFoundException {
        Order found = service.find(identifier);
        OrderDTO mapped = mapper.toDTO(found);
        return ResponseEntity.ok(mapped);
    }

    @PatchMapping(produces = "application/json", consumes = "application/json")
    @Override
    public ResponseEntity<OrderDTO> update(@RequestBody OrderDTO body) throws EntityNotFoundException {
        Order mapped = mapper.fromDTO(body);
        Order result = queueService.update(mapped, body.getItem(), body.getUser());
        OrderDTO dto = mapper.toDTO(result);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    @Override
    public ResponseEntity<Void> delete(@PathVariable("id") long identifier) throws EntityNotFoundException {
        service.delete(identifier);
        return ResponseEntity.ok().build();
    }
}
