package com.animesh245.social_medium.service.definition;

import com.animesh245.social_medium.dto.request.ReqLocationDto;
import com.animesh245.social_medium.dto.response.ResLocationDto;
import com.animesh245.social_medium.entity.Location;


import java.util.List;

public interface ILocationService
{
    List<ResLocationDto> getLocations();

    void saveLocation(ReqLocationDto location);

    ResLocationDto getLocation(String id) throws Exception;

    Location getLocationByName(String locationName) throws Exception;

//    void updateLocation(String id, ReqLocationDto location);

    void deleteLocation(String id) throws Exception;
}
