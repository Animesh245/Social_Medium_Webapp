package com.animesh245.social_medium.repository.implementation;

import com.animesh245.social_medium.entity.Location;
import com.animesh245.social_medium.repository.LocationRepoCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Repository
public class LocationRepoImpl implements LocationRepoCustom
{
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Location findByName(String locationName)
    {
        Query query = entityManager.createNativeQuery("FROM Location WHERE locationName = $P{name} ", Location.class);
        query.setParameter("name", locationName);

        return (Location) query.getSingleResult();
    }

}
