package com.dmtryii.wms.service;

import com.dmtryii.wms.dto.CityDTO;
import com.dmtryii.wms.exception.NotFoundException;
import com.dmtryii.wms.model.City;
import com.dmtryii.wms.model.OrderLine;
import com.dmtryii.wms.repository.CityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    public static final Logger LOG = LoggerFactory.getLogger(OrderLine.class);
    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public City createCity(CityDTO cityDTO) {
        City city = new City();
        city.setName(cityDTO.getName());
        city.setCountry(cityDTO.getCountry());
        city.setLongitude(cityDTO.getLongitude());
        city.setLatitude(cityDTO.getLatitude());

        LOG.info("The city of {} was created", city.getName());
        return cityRepository.save(city);
    }

    public City updateCity(Long cityId, CityDTO cityDTO) {
        City city = getCityById(cityId);
        city.setName(cityDTO.getName());
        city.setCountry(cityDTO.getCountry());
        city.setLongitude(cityDTO.getLongitude());
        city.setLatitude(cityDTO.getLatitude());

        LOG.info("The city of {} has been updated", city.getName());
        return cityRepository.save(city);
    }

    public List<City> getAllCity() {
        return cityRepository.findAll();
    }

    public City getCityById(Long id) {
        return cityRepository.findById(id).orElseThrow(
                () -> new NotFoundException("The city was not found by this id")
        );
    }

    public void deleteCity(Long cityId) {
        City city = getCityById(cityId);
        cityRepository.deleteById(cityId);
    }
}
