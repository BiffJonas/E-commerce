package com.ecommerce.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ecommerce.rest.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByNameContaining(String name);
}
