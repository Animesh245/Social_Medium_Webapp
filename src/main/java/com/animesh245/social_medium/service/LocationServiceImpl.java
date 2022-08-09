package com.animesh245.social_medium.service;

import com.animesh245.social_medium.dto.requestDto.ReqLocation;
import com.animesh245.social_medium.dto.responseDto.ResLocation;
import com.animesh245.social_medium.entity.Location;
import com.animesh245.social_medium.repository.LocationRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LocationServiceImpl implements LocationService
{
    private final LocationRepo locationRepo;


    public LocationServiceImpl(LocationRepo locationRepo1)
    {
        locationRepo=locationRepo1;
    }

    @Override
    public List<ResLocation> getLocations()
    {
        List<Location> locationList = locationRepo.findAll();
        List<ResLocation> resLocationList = new ArrayList<>();

        for (Location location: locationList) {
            ResLocation resLocation = new ResLocation();
            BeanUtils.copyProperties(location,resLocation);
            resLocationList.add(resLocation);
        }

        return resLocationList;
    }

    @Override
    public void saveLocation(ReqLocation reqLocation)
    {
        Location location = new Location();

        BeanUtils.copyProperties(reqLocation,location);

        locationRepo.save(location);
    }

    @Override
    public ResLocation getLocation(String id) throws Exception
    {
        Location location = locationRepo.findById(Long.parseLong(id)).orElseThrow();
        ResLocation resLocation = new ResLocation();

        BeanUtils.copyProperties(location, resLocation);
        return resLocation;
    }

    @Override
    public void updateLocation(String id, ReqLocation location)
    {
        Location location1 = locationRepo.findById(Long.parseLong(id)).orElseThrow();

        BeanUtils.copyProperties(location,location1);
        locationRepo.save(location1);
    }

    @Override
    public void deleteLocation(String id) throws Exception
    {
        locationRepo.deleteById(Long.parseLong(id));
    }
}
