package com.dmtryii.wms.controller;

import com.dmtryii.wms.model.City;
import com.dmtryii.wms.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/city")
public class CityController {
    private final CityService cityService;

    @GetMapping("/{city_id}")
    public ResponseEntity<City> getCityById(@PathVariable(name = "city_id") Long cityId) {
        City city = cityService.getCityById(cityId);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @GetMapping("/cities")
    public ResponseEntity<List<City>> getAllCity() {
        List<City> cities = cityService.getAllCity();
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<City> createCity(@RequestBody City cityRequest) {
        City city = cityService.createCity(cityRequest);
        return new ResponseEntity<>(city, HttpStatus.CREATED);
    }

    @PostMapping("/{city_id}")
    public ResponseEntity<City> updateCity(@PathVariable(name = "city_id") Long cityId,
                                           @RequestBody City cityRequest) {
        City city = cityService.updateCity(cityId, cityRequest);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @DeleteMapping("/{city_id}")
    public ResponseEntity<HttpStatus> deleteCity(@PathVariable(name = "city_id") Long cityId) {
        cityService.deleteCityById(cityId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
