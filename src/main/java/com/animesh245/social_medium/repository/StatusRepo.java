package com.animesh245.social_medium.repository;

import com.animesh245.social_medium.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepo extends JpaRepository<Status, Long> {
}
