package com.jjm.triphelper.controller;

import com.jjm.triphelper.controller.exceptions.PlaceNotFoundException;
import com.jjm.triphelper.entity.spec.Place;
import com.jjm.triphelper.entity.dto.PlaceDTO;
import com.jjm.triphelper.parser.PlaceParser;
import com.jjm.triphelper.repository.dto.PlaceDTORepository;
import com.jjm.triphelper.service.PlaceService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.Set;

@RestController
@RequestMapping(value = "api/place", produces = { MediaType.APPLICATION_JSON_VALUE })
public class PlaceController {

    @Resource private PlaceService placeService;
    @Resource private PlaceParser placeParser;
    @Resource private PlaceDTORepository placeDTORepository;

    @ApiOperation(
            value = "Fetch near places by name's location",
            notes = "<p>Fetch all places that are near of the location parameter </p>")
    @RequestMapping(value = "fetchNearPlacesByLocationName", method = RequestMethod.GET )
    public ResponseEntity<Set<PlaceDTO>> fetchNearPlacesByLocationName(@RequestParam(value = "location", required = true) final String location) {
        Set<Place> places = placeService.fetchNearPlacesByLocationName(location);
        if (places.isEmpty()) {
            throw new PlaceNotFoundException(location);
        }
        return new ResponseEntity<>(placeDTORepository.fetchNearPlacesByLocationName(places), HttpStatus.OK);
    }
}