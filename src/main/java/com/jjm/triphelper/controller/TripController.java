package com.jjm.triphelper.controller;

import com.jjm.triphelper.controller.exceptions.InvalidUserException;
import com.jjm.triphelper.controller.exceptions.PlaceNotFoundException;
import com.jjm.triphelper.entity.dto.TripDTO;
import com.jjm.triphelper.entity.spec.*;
import com.jjm.triphelper.repository.dto.TripRepositoryDTO;
import com.jjm.triphelper.service.*;
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
import java.util.Date;
import java.util.Set;

@RestController
@RequestMapping(value = "api/trip", produces = { MediaType.APPLICATION_JSON_VALUE })
public class TripController {

    @Resource private UserService userService;
    @Resource private TripService tripService;
    @Resource private TripRepositoryDTO tripDTORepository;

    @ApiOperation(value = "Fetch near places by name's location", notes = "<p>Fetch all places that are near of the location parameter </p>")
    @RequestMapping(value = "create", method = RequestMethod.POST )
    public ResponseEntity<TripDTO> create(@RequestParam(value = "userId", required = true) final String userId,
                                          @RequestParam(value = "location", required = true) final String location,
                                          @RequestParam(value = "startDate", required = true) final Date startDate,
                                          @RequestParam(value = "endDate", required = true) final Date endDate) {
        if (StringUtils.isEmpty(userId))
            throw new InvalidParameterException("The parameter userId must not be empty");
        if (StringUtils.isEmpty(location))
            throw new InvalidParameterException("The parameter location must not be empty");
        if (startDate == null)
            throw new InvalidParameterException("The parameter startDate must not be empty");
        if (endDate == null)
            throw new InvalidParameterException("The parameter endDate must not be empty");
        User user = userService.findById(userId);
        if (user == null)
            throw new InvalidUserException(userId);
        Trip trip = tripService.create(user, location, startDate, endDate);
        if (trip == null)
            throw new PlaceNotFoundException(location);
        return new ResponseEntity<>(tripDTORepository.find(trip), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Fetch all trips by user id", notes = "<p>Fetch all trips for the user</p>")
    @RequestMapping(value = "fetchAllByUser", method = RequestMethod.GET )
    public ResponseEntity<Set<TripDTO>> fetchAllByUser(@RequestParam(value = "userId", required = true) final String userId) {
        if (StringUtils.isEmpty(userId))
            throw new InvalidParameterException("The parameter userId must not be empty");
        User user = userService.findById(userId);
        if (user == null)
            throw new InvalidUserException(userId);
        return ResponseEntity.ok(tripDTORepository.findAll(user.getTrips()));
    }

}
