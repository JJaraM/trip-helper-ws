package com.jjm.triphelper.controller;

import com.jjm.triphelper.controller.exceptions.InvalidUserException;
import com.jjm.triphelper.entity.dto.TravelDTO;
import com.jjm.triphelper.entity.spec.Travel;
import com.jjm.triphelper.entity.spec.User;
import com.jjm.triphelper.repository.dto.TravelDTORepository;
import com.jjm.triphelper.service.TravelService;
import com.jjm.triphelper.service.UserService;
import io.swagger.annotations.ApiOperation;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/travel", produces = { MediaType.APPLICATION_JSON_VALUE })
public class TravelController {

    @Resource private UserService userService;
    @Resource private TravelDTORepository travelDTORepository;
    @Resource private TravelService travelService;

    @ApiOperation( value = "Fetch all travelers", notes = "<p>Fetch all travels</p>")
    @RequestMapping(value = "fetchAll", method = RequestMethod.GET )
    public ResponseEntity<Set<TravelDTO>> fetchAll(@RequestParam(value = "userId", required = true) final String userId) {
        User user = userService.findById(userId);
        if (user == null)
            throw new InvalidUserException(userId);
        return ResponseEntity.ok(travelDTORepository.findAll(sortByStartDate(user.getAllTravels())));
    }

    private Set<Travel> sortByStartDate(Set<Travel> travels) {
        return travels.stream().sorted((o1, o2) -> o1.getStartDate().compareTo(o2.getStartDate())).collect(Collectors.toSet());
    }

    @ApiOperation( value = "Schedule place", notes = "<p>Schedule a place in the current trip</p>")
    @RequestMapping(value = "schedulePlace", method = RequestMethod.POST)
    public ResponseEntity<TravelDTO> schedulePlace(@RequestParam(value = "tripId", required = true) final String tripId,
                                                  @RequestParam(value = "placeId", required = true) final String placeId,
                                                  @RequestParam(value = "startDate", required = false) final Date startDate,
                                                  @RequestParam(value = "endDate", required = false) final Date endDate) {
        if (StringUtils.isEmpty(tripId))
            throw new InvalidParameterException("The parameter tripId must not be empty");
        if (StringUtils.isEmpty(placeId))
            throw new InvalidParameterException("The parameter placeId not be empty");
        if (startDate == null)
            throw new InvalidParameterException("The parameter startDate must not be empty");
        if (endDate == null)
            throw new InvalidParameterException("The parameter endDate must not be empty");
        if (startDate.after(endDate))
            throw new InvalidParameterException("The start startDate must not be after to endDate");
        return ResponseEntity.ok(travelDTORepository.schedulePlace(travelService.schedule(tripId, placeId, startDate, endDate)));
    }

}
