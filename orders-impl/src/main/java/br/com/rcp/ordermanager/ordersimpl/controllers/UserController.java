package br.com.rcp.ordermanager.ordersimpl.controllers;

import br.com.rcp.ordermanager.orders.controllers.IController;
import br.com.rcp.ordermanager.orders.exceptions.EntityNotFoundException;
import br.com.rcp.ordermanager.orders.models.User;
import br.com.rcp.ordermanager.orders.services.UserService;
import br.com.rcp.ordermanager.ordersimpl.dto.UserDTO;
import br.com.rcp.ordermanager.ordersimpl.mappers.UserMapper;
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
@RequestMapping("/users")
public class UserController implements IController<UserDTO> {
    private final UserService service;
    private final UserMapper mapper;

    @Autowired
    public UserController(UserService service, UserMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    @Override
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO body) {
        User mapped = mapper.fromDTO(body);
        User result = service.create(mapped);
        UserDTO dto = mapper.toDTO(result);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(produces = "application/json")
    @Override
    public ResponseEntity<List<UserDTO>> find() {
        List<User> fetched = service.find();
        List<UserDTO> mapped = fetched.stream().map(mapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(mapped);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @Override
    public ResponseEntity<UserDTO> find(@PathVariable("id") long identifier) throws EntityNotFoundException {
        User found = service.find(identifier);
        UserDTO mapped = mapper.toDTO(found);
        return ResponseEntity.ok(mapped);
    }

    @PatchMapping(produces = "application/json", consumes = "application/json")
    @Override
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO body) throws EntityNotFoundException {
        User mapped = mapper.fromDTO(body);
        User result = service.update(mapped);
        UserDTO dto = mapper.toDTO(result);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    @Override
    public ResponseEntity<Void> delete(@PathVariable("id") long identifier) throws EntityNotFoundException {
        service.delete(identifier);
        return ResponseEntity.ok().build();
    }
}
