package br.com.rcp.ordermanager.orders.controllers;

import br.com.rcp.ordermanager.orders.dto.IDTO;
import br.com.rcp.ordermanager.orders.exceptions.EntityNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IController<T extends IDTO> {
    ResponseEntity<T> create(T body) throws EntityNotFoundException;

    ResponseEntity<List<T>> find();

    ResponseEntity<T> find(long identifier) throws EntityNotFoundException;

    ResponseEntity<T> update(T body) throws EntityNotFoundException;

    ResponseEntity<Void> delete(long identifier) throws EntityNotFoundException;
}
