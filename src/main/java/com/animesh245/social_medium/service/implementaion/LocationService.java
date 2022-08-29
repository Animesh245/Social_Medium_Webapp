package com.animesh245.social_medium.service.implementaion;

import com.animesh245.social_medium.dto.request.RequestLocationDto;
import com.animesh245.social_medium.dto.response.ResponseLocationDto;
import com.animesh245.social_medium.entity.Location;
import com.animesh245.social_medium.repository.LocationRepo;
import com.animesh245.social_medium.service.definition.ILocationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LocationService implements ILocationService
{
    private final LocationRepo locationRepo;

    private final ModelMapper modelMapper;

    public LocationService(LocationRepo locationRepo, ModelMapper modelMapper)
    {
        this.locationRepo=locationRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ResponseLocationDto> getLocations()
    {
        List<Location> locationList = locationRepo.findAll();
        var resLocationDtoList = new ArrayList<ResponseLocationDto>();

        for (Location location: locationList) {
            var resLocationDto = new ResponseLocationDto();
            resLocationDto = modelMapper.map(location, ResponseLocationDto.class);
            resLocationDtoList.add(resLocationDto);
        }

        return resLocationDtoList;
    }

    @Override
    public void saveLocation(RequestLocationDto requestLocationDto)
    {
        var location = new Location();

        location = modelMapper.map(requestLocationDto,Location.class);

        locationRepo.save(location);
    }

    @Override
    public ResponseLocationDto getLocation(String id)
    {
        Location location = locationRepo.findById(Long.parseLong(id)).orElseThrow();
        var resLocationDto = new ResponseLocationDto();

        resLocationDto = modelMapper.map(location, ResponseLocationDto.class);
        return resLocationDto;
    }

    @Override
    public Location getLocationByName(String locationName)
    {
        return locationRepo.findByLocationName(locationName);
    }

//    @Override
//    public void updateLocation(String id, ReqLocationDto location)
//    {
//        Location location1 = locationRepo.findById(Long.parseLong(id)).orElseThrow();
//
//        BeanUtils.copyProperties(location,location1);
//        locationRepo.save(location1);
//    }

    @Override
    public void deleteLocation(String id)
    {
        locationRepo.deleteById(Long.parseLong(id));
    }
}
