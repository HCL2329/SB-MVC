package com.lti.register.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.lti.register.model.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByName(String userName);
}
