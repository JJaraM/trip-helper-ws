package com.jjm.triphelper.controller;

import com.jjm.triphelper.controller.exceptions.InvalidUserException;
import com.jjm.triphelper.entity.dto.PlaceDTO;
import com.jjm.triphelper.entity.dto.TravelDTO;
import com.jjm.triphelper.entity.dto.TripDTO;
import com.jjm.triphelper.entity.spec.Travel;
import com.jjm.triphelper.entity.spec.Trip;
import com.jjm.triphelper.entity.spec.User;
import com.jjm.triphelper.repository.dto.TravelDTORepository;
import com.jjm.triphelper.repository.dto.TripRepositoryDTO;
import com.jjm.triphelper.service.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.Date;
import java.util.Set;

@RestController
@RequestMapping(value = "api/trip", produces = { MediaType.APPLICATION_JSON_VALUE })
public class TripController {

    @Resource private UserService userService;
    @Resource private TripService tripService;
    @Resource private TripRepositoryDTO tripDTORepository;
    @Resource private CryptoService cryptoService;


    @ApiOperation(
            value = "Fetch near places by name's location",
            notes = "<p>Fetch all places that are near of the location parameter </p>")
    @RequestMapping(value = "create", method = RequestMethod.POST )
    public ResponseEntity<TripDTO> create(@RequestParam(value = "userId", required = true) final String userId,
                                          @RequestParam(value = "location", required = true) final String location,
                                          @RequestParam(value = "startDate", required = true) final Date startDate,
                                          @RequestParam(value = "endDate", required = true) final Date endDate) {
        User user = userService.findById(userId);
        if (user == null) {
            throw new InvalidUserException(userId);
        }
        return new ResponseEntity<>(tripDTORepository.find(tripService.create(user, location, startDate, endDate)), HttpStatus.CREATED);
    }

    @RequestMapping(value = "fetchAllByUser", method = RequestMethod.GET )
    public ResponseEntity<Set<TripDTO>> fetchAllByUser(@RequestParam(value = "userId", required = true) final String userId) {
        User user = userService.findById(userId);
        if (user == null) {
            throw new InvalidUserException(userId);
        }
        return ResponseEntity.ok(tripDTORepository.findAll(user.getTrips()));
    }

}
