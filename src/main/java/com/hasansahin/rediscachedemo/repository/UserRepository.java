package com.hasansahin.rediscachedemo.repository;

import com.hasansahin.rediscachedemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
