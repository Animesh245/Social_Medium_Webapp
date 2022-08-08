package com.animesh245.social_medium.service;

import com.animesh245.social_medium.dto.requestDto.ReqLocation;
import com.animesh245.social_medium.dto.responseDto.ResLocation;
import com.animesh245.social_medium.entity.Location;

import java.util.List;

public interface LocationService 
{
    List<Location> getLocations();

    void saveLocation(ReqLocation location);

    ResLocation getLocation(String id) throws Exception;

    void updateLocation(String id, ReqLocation location);

    void deleteLocation(String id) throws Exception;
}