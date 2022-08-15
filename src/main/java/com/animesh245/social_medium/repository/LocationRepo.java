package com.animesh245.social_medium.repository;

import com.animesh245.social_medium.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface LocationRepo extends JpaRepository<Location, Long>
{
    List<Location> findByLocationName(String locationName);

}
