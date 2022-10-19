package com.animesh245.social_medium.repository;

import com.animesh245.social_medium.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long>
{
    User findByEmailId(String email);
}
