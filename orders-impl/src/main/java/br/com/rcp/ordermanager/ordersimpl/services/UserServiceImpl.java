package br.com.rcp.ordermanager.ordersimpl.services;

import br.com.rcp.ordermanager.orders.models.User;
import br.com.rcp.ordermanager.orders.services.UserService;
import br.com.rcp.ordermanager.ordersimpl.repository.UserRepository;
import br.com.rcp.ordermanager.ordersimpl.services.base.AbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends AbstractServiceImpl<User, UserRepository> implements UserService {

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }

}
