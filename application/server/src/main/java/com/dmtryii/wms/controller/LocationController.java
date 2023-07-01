package com.dmtryii.wms.controller;

import com.dmtryii.wms.model.Location;
import com.dmtryii.wms.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/location")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @GetMapping
    public ResponseEntity<List<Location>> getAllLocation() {
        List<Location> locations = locationService.getAllLocation();
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }
}
