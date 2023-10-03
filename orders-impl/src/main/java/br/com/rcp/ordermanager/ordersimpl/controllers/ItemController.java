package br.com.rcp.ordermanager.ordersimpl.controllers;

import br.com.rcp.ordermanager.orders.controllers.IController;
import br.com.rcp.ordermanager.orders.exceptions.EntityNotFoundException;
import br.com.rcp.ordermanager.orders.models.Item;
import br.com.rcp.ordermanager.orders.services.ItemService;
import br.com.rcp.ordermanager.ordersimpl.dto.ItemDTO;
import br.com.rcp.ordermanager.ordersimpl.mappers.ItemMapper;
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
@RequestMapping("/items")
public class ItemController implements IController<ItemDTO> {
    private final ItemService service;
    private final ItemMapper mapper;

    @Autowired
    public ItemController(ItemService service, ItemMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    @Override
    public ResponseEntity<ItemDTO> create(@RequestBody ItemDTO body) {
        Item mapped = mapper.fromDTO(body);
        Item result = service.create(mapped);
        ItemDTO dto = mapper.toDTO(result);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(produces = "application/json")
    @Override
    public ResponseEntity<List<ItemDTO>> find() {
        List<Item> fetched = service.find();
        List<ItemDTO> mapped = fetched.stream().map(mapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(mapped);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @Override
    public ResponseEntity<ItemDTO> find(@PathVariable("id") long identifier) throws EntityNotFoundException {
        Item found = service.find(identifier);
        ItemDTO mapped = mapper.toDTO(found);
        return ResponseEntity.ok(mapped);
    }

    @PatchMapping(produces = "application/json", consumes = "application/json")
    @Override
    public ResponseEntity<ItemDTO> update(@RequestBody ItemDTO body) throws EntityNotFoundException {
        Item mapped = mapper.fromDTO(body);
        Item result = service.update(mapped);
        ItemDTO dto = mapper.toDTO(result);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    @Override
    public ResponseEntity<Void> delete(@PathVariable("id") long identifier) throws EntityNotFoundException {
        service.delete(identifier);
        return ResponseEntity.ok().build();
    }
}
