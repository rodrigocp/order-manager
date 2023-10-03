package br.com.rcp.ordermanager.ordersimpl.mappers;

import br.com.rcp.ordermanager.orders.mappers.IMapper;
import br.com.rcp.ordermanager.orders.models.User;
import br.com.rcp.ordermanager.ordersimpl.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements IMapper<User, UserDTO> {
    @Override
    public UserDTO toDTO(User entity) {
        UserDTO data = new UserDTO();

        data.setIdentifier(entity.getIdentifier());
        data.setName(entity.getName());
        data.setEmail(entity.getEmail());

        return data;
    }

    @Override
    public User fromDTO(UserDTO data) {
        User entity = new User();

        entity.setIdentifier(data.getIdentifier());
        entity.setName(data.getName());
        entity.setEmail(data.getEmail());

        return entity;
    }
}
