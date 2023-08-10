package com.dmtryii.wms.service;

import com.dmtryii.wms.exception.ResourceNotFoundException;
import com.dmtryii.wms.model.City;
import com.dmtryii.wms.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {

    public static final Logger LOG = LoggerFactory.getLogger(City.class);
    private final CityRepository cityRepository;

    public City createCity(City cityRequest) {
        City city = City.builder()
                .name(cityRequest.getName())
                .countryName(cityRequest.getCountryName())
                .build();
        LOG.info("The city of {} was created", city.getName());
        return cityRepository.save(city);
    }

    public City updateCity(Long cityId, City cityRequest) {
        City city = getCityById(cityId);
        city.setName(cityRequest.getName());
        city.setCountryName(cityRequest.getCountryName());

        LOG.info("The city {} was updated", city.getName());
        return cityRepository.save(city);
    }

    public List<City> getAllCity() {
        return cityRepository.findAll();
    }

    public City getCityById(Long cityId) {
        return cityRepository.findById(cityId).orElseThrow(
                () -> new ResourceNotFoundException("The city not fount by id: " + cityId)
        );
    }

    public void deleteCityById(Long cityId) {
        City city = getCityById(cityId);
        cityRepository.delete(city);
        LOG.info("The city from id {} was deleted", cityId);
    }
}
