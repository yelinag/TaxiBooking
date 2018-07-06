package taxibooking.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import taxibooking.dto.BookedDto;
import taxibooking.dto.BookingDto;
import taxibooking.dto.MessageDto;
import taxibooking.service.BookingService;
import taxibooking.service.TimeService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ApiController {

    private final BookingService bookingService;
    private final TimeService timeService;

    @RequestMapping(method = RequestMethod.POST, value ="/book", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Book Taxi", notes="Book available taxi from vehicles list or response null if no matching found",
        response = BookedDto.class)
    public BookedDto bookTaxi(@ApiParam(value="booking request entity", required = true) @RequestBody BookingDto requestBooking){
        return this.bookingService.bookTaxi(requestBooking);
    }

    @RequestMapping(method = RequestMethod.POST, value="/tick")
    @ApiOperation(value = "Tick Time", notes="Tick time by one in order to move vehicles in virtual environment. " +
            " Response success message if operation is successful", response = MessageDto.class)
    public MessageDto tickTimeByOne(){
        timeService.tickTimeByOne();
        return new MessageDto("success");
    }

    @RequestMapping(method = RequestMethod.PUT, value="/reset")
    @ApiOperation(value = "Reset All Vehicles", notes="Reset all vehicles to default states and positions." +
            " Response success message if operation is successful", response = MessageDto.class)
    public MessageDto resetAllVehicles(){
        bookingService.initializeAll();
        return new MessageDto("success");
    }

}
