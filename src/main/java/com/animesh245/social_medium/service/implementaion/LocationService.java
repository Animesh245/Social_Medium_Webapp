package com.animesh245.social_medium.service.implementaion;

import com.animesh245.social_medium.dto.request.ReqLocationDto;
import com.animesh245.social_medium.dto.response.ResLocationDto;
import com.animesh245.social_medium.entity.Location;
import com.animesh245.social_medium.repository.LocationRepo;
import com.animesh245.social_medium.service.definition.ILocationService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LocationService implements ILocationService
{
    private final LocationRepo locationRepo;


    public LocationService(LocationRepo locationRepo1)
    {
        locationRepo=locationRepo1;
    }

    @Override
    public List<ResLocationDto> getLocations()
    {
        List<Location> locationList = locationRepo.findAll();
        List<ResLocationDto> resLocationDtoList = new ArrayList<>();

        for (Location location: locationList) {
            ResLocationDto resLocationDto = new ResLocationDto();
            BeanUtils.copyProperties(location, resLocationDto);
            resLocationDtoList.add(resLocationDto);
        }

        return resLocationDtoList;
    }

    @Override
    public void saveLocation(ReqLocationDto reqLocationDto)
    {
        Location location = new Location();

        BeanUtils.copyProperties(reqLocationDto,location);

        locationRepo.save(location);
    }

    @Override
    public ResLocationDto getLocation(String id) throws Exception
    {
        Location location = locationRepo.findById(Long.parseLong(id)).orElseThrow();
        ResLocationDto resLocationDto = new ResLocationDto();

        BeanUtils.copyProperties(location, resLocationDto);
        return resLocationDto;
    }

    @Override
    public void updateLocation(String id, ReqLocationDto location)
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
