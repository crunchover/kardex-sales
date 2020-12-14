package com.pipecode.kardexsales.repository;


import com.pipecode.kardexsales.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

    User findByUserName(String userName);
}
