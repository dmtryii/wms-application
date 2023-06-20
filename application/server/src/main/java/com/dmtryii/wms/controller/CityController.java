package com.dmtryii.wms.controller;

import com.dmtryii.wms.model.City;
import com.dmtryii.wms.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/city")
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @GetMapping("{city_id}")
    public ResponseEntity<City> getCityById(@PathVariable(name = "city_id") String id) {

        City city = cityRepository.findById(id).orElseThrow();

        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<City>> getAllCity() {

        List<City> cities = cityRepository.findAll();

        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<City> createCity(@RequestBody City city) {

        City _city = cityRepository.save(new City(
                city.getId(),
                city.getName(),
                city.getCountry(),
                city.getLongitude(),
                city.getLatitude()
        ));

        return new ResponseEntity<>(_city, HttpStatus.CREATED);
    }

    @DeleteMapping("{city_id}")
    public ResponseEntity<HttpStatus> deleteCity(@PathVariable(name = "city_id") String id) {

        cityRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
