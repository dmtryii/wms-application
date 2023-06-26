package com.dmtryii.wms.controller;

import com.dmtryii.wms.dto.CityDTO;
import com.dmtryii.wms.model.City;
import com.dmtryii.wms.repository.CityRepository;
import com.dmtryii.wms.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/city")
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping("{city_id}")
    public ResponseEntity<City> getCityById(@PathVariable(name = "city_id") Long id) {

        City city = cityService.getCityById(id);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<City>> getAllCity() {

        List<City> cities = cityService.getAllCity();
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<City> createCity(@RequestBody CityDTO cityDTO) {

        City city = cityService.createCity(cityDTO);
        return new ResponseEntity<>(city, HttpStatus.CREATED);
    }

    @PostMapping("{city_id}")
    public ResponseEntity<City> updateCity(@PathVariable(name = "city_id") Long cityId,
                                           @RequestBody CityDTO cityDTO) {

        City city = cityService.updateCity(cityId, cityDTO);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @DeleteMapping("{city_id}")
    public ResponseEntity<HttpStatus> deleteCity(@PathVariable(name = "city_id") Long id) {
        cityService.deleteCity(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
