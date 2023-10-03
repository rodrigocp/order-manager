package br.com.rcp.ordermanager.ordersimpl.services.base;

import br.com.rcp.ordermanager.orders.exceptions.EntityNotFoundException;
import br.com.rcp.ordermanager.orders.models.IEntity;
import br.com.rcp.ordermanager.orders.services.IService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class AbstractServiceImpl<T extends IEntity, R extends JpaRepository<T, Long>> implements IService<T> {

    private final R repository;

    public AbstractServiceImpl(R repository) {
        this.repository = repository;
    }

    @Override
    public T create(T entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    public List<T> find() {
        return repository.findAll();
    }

    @Override
    public T find(long identifier) throws EntityNotFoundException {
        return repository.findById(identifier).orElseThrow(() -> new EntityNotFoundException(identifier));
    }

    @Override
    public T update(T entity) throws EntityNotFoundException {
        if (!repository.existsById(entity.getIdentifier())) {
            throw new EntityNotFoundException(entity.getIdentifier());
        }

        return repository.saveAndFlush(entity);
    }

    @Override
    public void delete(long identifier) throws EntityNotFoundException {
        repository.delete(find(identifier));
    }

    protected R getRepository() {
        return repository;
    }
}
