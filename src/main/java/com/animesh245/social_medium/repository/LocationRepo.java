package com.animesh245.social_medium.repository;

import com.animesh245.social_medium.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LocationRepo extends JpaRepository<Location, Long>
{
    Location findByLocationName(String locationName);

}
