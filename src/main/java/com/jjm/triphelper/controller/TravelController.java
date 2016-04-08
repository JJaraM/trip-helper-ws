package com.jjm.triphelper.controller;

import com.jjm.triphelper.controller.exceptions.InvalidUserException;
import com.jjm.triphelper.entity.dto.TravelDTO;
import com.jjm.triphelper.entity.spec.Trip;
import com.jjm.triphelper.entity.spec.User;
import com.jjm.triphelper.repository.dto.TravelDTORepository;
import com.jjm.triphelper.service.TravelService;
import com.jjm.triphelper.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.Date;
import java.util.Set;
import static java.util.stream.Collectors.*;

@RestController
@RequestMapping(value = "api/travel", produces = { MediaType.APPLICATION_JSON_VALUE })
public class TravelController {

    @Resource private UserService userService;
    @Resource private TravelDTORepository travelDTORepository;
    @Resource private TravelService travelService;

    @ApiOperation(
            value = "Fetch all travelers",
            notes = "<p>Fetch all travels</p>")
    @RequestMapping(value = "fetchAll", method = RequestMethod.GET )
    public ResponseEntity<Set<TravelDTO>> fetchAll(@RequestParam(value = "userId", required = true) final String userId) {
        User user = userService.findById(userId);
        if (user == null) {
            throw new InvalidUserException(userId);
        }
        return ResponseEntity.ok(travelDTORepository.findAll(
                user.getTrips().stream().map(Trip::getTravels).flatMap(travels -> travels.stream())
                        .sorted((o1, o2) -> o1.getStartDate().compareTo(o2.getStartDate())).collect(toSet()))
        );
    }

    @ApiOperation(
            value = "Schedule place",
            notes = "<p>Schedule a place in the current trip</p>")
    @RequestMapping(value = "schedulePlace", method = RequestMethod.POST)
    public ResponseEntity<TravelDTO> schedulePlace(@RequestParam(value = "tripId", required = true) final String tripId,
                                                  @RequestParam(value = "placeId", required = true) final String placeId,
                                                  @RequestParam(value = "startDate", required = true) final Date startDate,
                                                  @RequestParam(value = "endDate", required = true) final Date endDate) {
        return ResponseEntity.ok(travelDTORepository.schedulePlace(travelService.schedule(tripId, placeId, startDate, endDate)));
    }

}
