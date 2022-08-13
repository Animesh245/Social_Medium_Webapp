package com.animesh245.social_medium.repository;

import com.animesh245.social_medium.entity.Location;

public interface LocationRepoCustom
{
    Location findByName(String locationName);
}
