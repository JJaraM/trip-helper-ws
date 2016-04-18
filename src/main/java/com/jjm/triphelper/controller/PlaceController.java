package com.jjm.triphelper.controller;

import com.jjm.triphelper.controller.exceptions.PlaceNotFoundException;
import com.jjm.triphelper.controller.exceptions.TripNotFoundException;
import com.jjm.triphelper.entity.spec.Place;
import com.jjm.triphelper.entity.dto.PlaceDTO;
import com.jjm.triphelper.entity.spec.Travel;
import com.jjm.triphelper.entity.spec.Trip;
import com.jjm.triphelper.parser.PlaceParser;
import com.jjm.triphelper.repository.dto.PlaceDTORepository;
import com.jjm.triphelper.service.PlaceService;
import com.jjm.triphelper.service.TripService;
import com.jjm.triphelper.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/place", produces = { MediaType.APPLICATION_JSON_VALUE })
public class PlaceController {

    @Resource private PlaceService placeService;
    @Resource private PlaceDTORepository placeDTORepository;
    @Resource private TripService tripService;

    @ApiOperation(
            value = "Fetch near places by name's location",
            notes = "<p>Fetch all places that are near of the location parameter </p>")
    @RequestMapping(value = "fetchNearPlacesByLocationName", method = RequestMethod.GET )
    public ResponseEntity<Set<PlaceDTO>> fetchNearPlacesByLocationName(@RequestParam(value = "tripId", required = true) final String tripId) {
        if (StringUtils.isEmpty(tripId))
            throw new InvalidParameterException("The parameter tripId must not be empty");
        Trip trip = tripService.findById(tripId);
        if (trip == null)
            throw new TripNotFoundException(tripId);
        Set<Place> places = placeService.fetchNearPlacesByLocationName(trip.getTripInfo().getPlaceLocation());
        if (places.isEmpty())
            throw new PlaceNotFoundException(trip.getTripInfo().getPlaceLocation());
        return new ResponseEntity<>(placeDTORepository.fetchNearPlacesByLocationName(places), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Fetch near places by name's location",
            notes = "<p>Fetch all places that are near of the location parameter </p>")
    @RequestMapping(value = "fetchNearPlacesByLocationNameAndCategory", method = RequestMethod.GET )
    public ResponseEntity<Set<PlaceDTO>> fetchNearPlacesByLocationNameAndCategory(
            @RequestParam(value = "tripId", required = true) final String tripId,
            @RequestParam(value = "categoryId", required = true) final String categoryId) {
        if (StringUtils.isEmpty(tripId))
            throw new InvalidParameterException("The parameter tripId must not be empty");
        if (StringUtils.isEmpty(categoryId))
            throw new InvalidParameterException("The parameter categoryId must not be empty");
        Trip trip = tripService.findById(tripId);
        if (trip == null)
            throw new TripNotFoundException(tripId);
        Set<Place> places = placeService.fetchNearPlacesByLocationNameAndCategoryId(trip.getTripInfo().getPlaceLocation(), categoryId)
                .stream().filter(originalPlace -> !trip.getTravels().stream().map(Travel::getPlace).collect(Collectors.toSet()).contains(originalPlace)
        ).collect(Collectors.toSet());
        if (places.isEmpty())
            throw new PlaceNotFoundException(trip.getTripInfo().getPlaceLocation());
        return new ResponseEntity<>(sortByRating(placeDTORepository.fetchNearPlacesByLocationName(places)), HttpStatus.OK);
    }

    public Set<PlaceDTO> sortByRating(final Set<PlaceDTO> places) {
        return places.stream().sorted((o1, o2) -> o1.getRating().toString().compareTo(o2.getRating().toString())).collect(Collectors.toCollection(LinkedHashSet::new));
    }


    @RequestMapping(value = "fetchLastPlacesByTripAndCategories", method = RequestMethod.GET )
    public ResponseEntity<Set<PlaceDTO>> fetchLastPlacesByTripAndCategories(@RequestParam(value = "tripId", required = true) String tripId,
                                                                            @RequestParam(value = "categories", required = true) String[] categories) {
        if (StringUtils.isEmpty(tripId))
            throw new InvalidParameterException("The parameter tripId must not be empty");
        Trip trip = tripService.findById(tripId);
        Set<Place> places = new HashSet<>();
        for (String category : categories) {
            Place place = placeService.findByLocationAndCategories(trip.getTripInfo().getPlaceLocation(), category);
            if (place != null)
                places.add(placeService.findByLocationAndCategories(trip.getTripInfo().getPlaceLocation(), category));
        }
        places = places.stream().filter(originalPlace ->
                !trip.getTravels().stream().map(Travel::getPlace).collect(Collectors.toSet()).contains(originalPlace)
        ).collect(Collectors.toSet());
        if (places.isEmpty())
            throw new PlaceNotFoundException(trip.getTripInfo().getPlaceLocation());
        return new ResponseEntity<>(sortByCategoryName(placeDTORepository.fetchNearPlacesByLocationNameAndCategory(places)), HttpStatus.OK);
    }

    public Set<PlaceDTO> sortByCategoryName(Set<PlaceDTO> places) {
        return places.stream().sorted((o1, o2) -> o1.getCategory().getName().compareTo(o2.getCategory().getName())).collect(Collectors.toCollection(LinkedHashSet::new));
    }

}