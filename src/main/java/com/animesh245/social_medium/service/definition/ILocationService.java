package com.animesh245.social_medium.service.definition;

import com.animesh245.social_medium.dto.request.RequestLocationDto;
import com.animesh245.social_medium.dto.response.ResponseLocationDto;
import com.animesh245.social_medium.entity.Location;


import java.util.List;

public interface ILocationService
{
    List<ResponseLocationDto> getLocations();

    void saveLocation(RequestLocationDto location);

    ResponseLocationDto getLocation(String id) throws Exception;

    Location getLocationByName(String locationName) throws Exception;

//    void updateLocation(String id, ReqLocationDto location);

    void deleteLocation(String id) throws Exception;
}
