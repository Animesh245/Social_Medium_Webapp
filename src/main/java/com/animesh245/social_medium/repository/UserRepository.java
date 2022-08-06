package com.animesh245.social_medium.repository;

import com.animesh245.social_medium.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
