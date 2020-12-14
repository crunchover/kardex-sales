package com.pipecode.kardexsales.usecase.user;

import com.pipecode.kardexsales.repository.UserRepository;
import com.pipecode.kardexsales.model.entity.User;
import com.pipecode.kardexsales.model.web.CreateUserRequest;
import com.pipecode.kardexsales.validator.GenericRequestValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CreateUserImpl implements CreateUser {

    private final GenericRequestValidator validator;

    private final UserRepository userRepository;

    @Override
    public void apply(CreateUserRequest request) {

        validator.accept(request);

        //Todo:// Fuera de scope realizar mejores validaciones
        User user= new User();
        user.setUserName(request.getUserName());
        user.setActive(request.getIsActive());

        userRepository.save(user);

    }
}
