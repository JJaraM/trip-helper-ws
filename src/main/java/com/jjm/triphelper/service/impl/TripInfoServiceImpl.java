package com.jjm.triphelper.service.impl;

import com.jjm.triphelper.entity.spec.TripInfo;
import com.jjm.triphelper.repository.impl.TripInfoRepositoryJPA;
import com.jjm.triphelper.service.TripInfoService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class TripInfoServiceImpl implements TripInfoService {

    @Resource private TripInfoRepositoryJPA tripInfoRepository;

    @Override
    public TripInfo findByPlaceLocation(String placeLocation) {
        return tripInfoRepository.findByPlaceLocation(placeLocation);
    }

    @Override
    public TripInfo save(String placeLocation) {
        return tripInfoRepository.save(placeLocation);
    }

}
