package com.pipecode.kardexsales.usecase.user;

import com.pipecode.kardexsales.model.web.CreateUserRequest;

@FunctionalInterface
public interface CreateUser {
    void apply(CreateUserRequest request);
}
