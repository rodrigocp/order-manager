package br.com.rcp.ordermanager.ordersimpl.controllers;

import br.com.rcp.ordermanager.orders.controllers.IController;
import br.com.rcp.ordermanager.orders.exceptions.EntityNotFoundException;
import br.com.rcp.ordermanager.orders.models.StockMovement;
import br.com.rcp.ordermanager.orders.services.StockMovementService;
import br.com.rcp.ordermanager.ordersimpl.dto.StockMovementDTO;
import br.com.rcp.ordermanager.ordersimpl.mappers.StockMovementMapper;
import br.com.rcp.ordermanager.ordersimpl.services.base.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/stock-movements")
public class StockMovementController implements IController<StockMovementDTO> {

    private final StockMovementService service;

    private final QueueService queueService;

    private final StockMovementMapper mapper;

    @Autowired
    public StockMovementController(StockMovementService service, QueueService queueService, StockMovementMapper mapper) {
        this.queueService = queueService;
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    @Override
    public ResponseEntity<StockMovementDTO> create(@RequestBody StockMovementDTO body) throws EntityNotFoundException {
        StockMovement mapped = mapper.fromDTO(body);
        StockMovement result = service.create(mapped, body.getItem());
        StockMovementDTO dto = mapper.toDTO(result);
        queueService.notifyStockMovementUpdated(result);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(produces = "application/json")
    @Override
    public ResponseEntity<List<StockMovementDTO>> find() {
        List<StockMovement> fetched = service.find();
        List<StockMovementDTO> mapped = fetched.stream().map(mapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(mapped);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @Override
    public ResponseEntity<StockMovementDTO> find(@PathVariable("id") long identifier) throws EntityNotFoundException {
        StockMovement found = service.find(identifier);
        StockMovementDTO mapped = mapper.toDTO(found);
        return ResponseEntity.ok(mapped);
    }

    @PatchMapping(produces = "application/json", consumes = "application/json")
    @Override
    public ResponseEntity<StockMovementDTO> update(@RequestBody StockMovementDTO body) throws EntityNotFoundException {
        StockMovement mapped = mapper.fromDTO(body);
        StockMovement result = service.update(mapped, body.getItem());
        StockMovementDTO dto = mapper.toDTO(result);
        queueService.notifyStockMovementUpdated(result);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    @Override
    public ResponseEntity<Void> delete(@PathVariable("id") long identifier) throws EntityNotFoundException {
        service.delete(identifier);
        return ResponseEntity.ok().build();
    }
}
