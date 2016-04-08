package com.jjm.triphelper.controller;

import com.jjm.triphelper.controller.exceptions.PlaceNotFoundException;
import com.jjm.triphelper.entity.spec.Place;
import com.jjm.triphelper.entity.dto.PlaceDTO;
import com.jjm.triphelper.entity.spec.Travel;
import com.jjm.triphelper.entity.spec.Trip;
import com.jjm.triphelper.entity.spec.User;
import com.jjm.triphelper.parser.PlaceParser;
import com.jjm.triphelper.repository.dto.PlaceDTORepository;
import com.jjm.triphelper.service.PlaceService;
import com.jjm.triphelper.service.TripService;
import com.jjm.triphelper.service.UserService;
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
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@RestController
@RequestMapping(value = "api/place", produces = { MediaType.APPLICATION_JSON_VALUE })
public class PlaceController {

    @Resource private PlaceService placeService;
    @Resource private PlaceParser placeParser;
    @Resource private PlaceDTORepository placeDTORepository;
    @Resource private TripService tripService;
    @Resource private UserService userService;

    @ApiOperation(
            value = "Fetch near places by name's location",
            notes = "<p>Fetch all places that are near of the location parameter </p>")
    @RequestMapping(value = "fetchNearPlacesByLocationName", method = RequestMethod.GET )
    public ResponseEntity<Set<PlaceDTO>> fetchNearPlacesByLocationName(
            @RequestParam(value = "tripId", required = true) final String tripId) {
        Trip trip = tripService.findById(tripId);
        Set<Place> places = placeService.fetchNearPlacesByLocationName(trip.getPlaceLocation());
        if (places.isEmpty()) {
            throw new PlaceNotFoundException(trip.getPlaceLocation());
        }
        return new ResponseEntity<>(placeDTORepository.fetchNearPlacesByLocationName(places), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Fetch near places by name's location",
            notes = "<p>Fetch all places that are near of the location parameter </p>")
    @RequestMapping(value = "fetchNearPlacesByLocationNameAndCategory", method = RequestMethod.GET )
    public ResponseEntity<Set<PlaceDTO>> fetchNearPlacesByLocationNameAndCategory(
            @RequestParam(value = "tripId", required = true) final String tripId,
            @RequestParam(value = "categoryId", required = true) final String categoryId) {
        Trip trip = tripService.findById(tripId);
        Set<Place> places = placeService.fetchNearPlacesByLocationNameAndCategoryId(trip.getPlaceLocation(), categoryId);
        if (places.isEmpty()) {
            throw new PlaceNotFoundException(trip.getPlaceLocation());
        }

        places = places.stream().filter(originalPlace ->
                !trip.getTravels().stream().map(Travel::getPlace).collect(Collectors.toSet()).contains(originalPlace)
        ).collect(Collectors.toSet());

        if (places.isEmpty()) {
            throw new PlaceNotFoundException(trip.getPlaceLocation());
        }

        return new ResponseEntity<>(placeDTORepository.fetchNearPlacesByLocationName(places), HttpStatus.OK);
    }


}