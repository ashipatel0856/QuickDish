package com.ashish.QuickDish.controller;

import com.ashish.QuickDish.dto.DIstanceResponseDto;
import com.ashish.QuickDish.dto.DistanceRequestDto;
import com.ashish.QuickDish.service.MapService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/map/distance/time")
public class MapController {

    private final MapService mapService;

    public MapController(MapService mapService) {
        this.mapService = mapService;
    }

    @PostMapping
    public ResponseEntity<DistanceRequestDto> findDistance(@RequestBody DistanceRequestDto distanceRequestDto) {
        return ResponseEntity.ok(mapService.findDistance(distanceRequestDto));
    }
}
