package taxibooking.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import taxibooking.bo.Coordinate;
import taxibooking.bo.Customer;
import taxibooking.bo.Trip;
import taxibooking.bo.Vehicle;
import taxibooking.dao.TripDao;
import taxibooking.dao.VehicleDao;
import taxibooking.dto.BookedDto;
import taxibooking.dto.BookingDto;
import taxibooking.dto.CoordinateDto;
import taxibooking.mapper.CoordinateMapper;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class BookingServiceImplTest {

    @InjectMocks
    private BookingServiceImpl sut;

    @Mock
    private VehicleDao vehicleDao;

    @Mock
    private TripDao tripDao;

    @Test
    public void testBookTaxiWhenSuccess(){
        Map<Integer, Vehicle> vehicles = new HashMap<>();
        vehicles.put(1, new Vehicle(1, false, new Coordinate(1,1)));
        vehicles.put(2, new Vehicle(2, false, new Coordinate(0,0)));
        vehicles.put(3, new Vehicle(3, true, new Coordinate(10,10)));

        int selectedVehicle = 1;
        Vehicle vehicle = new Vehicle(selectedVehicle, false, new Coordinate(1,1));
        Coordinate destination = new Coordinate(-12, -12);
        Customer customer = new Customer(new Coordinate(12,12));

        when(vehicleDao.getAllVehicles()).thenReturn(vehicles);
        when(tripDao.insertNewTrip(any(Vehicle.class), any(Coordinate.class), any(Customer.class)))
                .thenReturn(new Trip(1, vehicle, destination, customer));


        BookingDto bookingDto = new BookingDto(CoordinateMapper.convertToCoordinateDto(customer.getStartingPosition()),
                CoordinateMapper.convertToCoordinateDto(destination));
        BookedDto bookedDto = sut.bookTaxi(bookingDto);

        verify(tripDao, times(1)).insertNewTrip(any(), any(), any());
        Assert.assertEquals(new Integer(selectedVehicle), bookedDto.getCar_id());
        Assert.assertEquals(new Long(70), bookedDto.getTotal_time());
    }

    @Test
    public void testBookTaxiWhenResponseEmpty(){
        Map<Integer, Vehicle> vehicles = new HashMap<>();
        vehicles.put(1, new Vehicle(1, true, new Coordinate(1,1)));
        vehicles.put(2, new Vehicle(2, true, new Coordinate(0,0)));
        vehicles.put(3, new Vehicle(3, true, new Coordinate(10,10)));
        Coordinate destination = new Coordinate(-12, -12);
        Customer customer = new Customer(new Coordinate(12,12));

        when(vehicleDao.getAllVehicles()).thenReturn(vehicles);


        BookingDto bookingDto = new BookingDto(CoordinateMapper.convertToCoordinateDto(customer.getStartingPosition()),
                CoordinateMapper.convertToCoordinateDto(destination));
        BookedDto bookedDto = sut.bookTaxi(bookingDto);

        Assert.assertEquals(null, bookedDto.getCar_id());
        Assert.assertEquals(null, bookedDto.getTotal_time());
    }

    @Test
    public void testGetNearestVehicleWhenSuccess(){
        Map<Integer, Vehicle> vehicles = new HashMap<>();
        vehicles.put(1, new Vehicle(1, false, new Coordinate(1,1)));
        vehicles.put(2, new Vehicle(2, false, new Coordinate(0,0)));
        vehicles.put(3, new Vehicle(3, true, new Coordinate(10,10)));
        when(vehicleDao.getAllVehicles()).thenReturn(vehicles);

        Vehicle actualResult = sut.getNearestVehicle(new Coordinate(12,12));
        Assert.assertEquals(vehicles.get(1), actualResult);
    }

    @Test
    public void testGetNearestVehicleWhenResponseNull(){
        Map<Integer, Vehicle> vehicles = new HashMap<>();
        vehicles.put(1, new Vehicle(1, true, new Coordinate(1,1)));
        vehicles.put(2, new Vehicle(2, true, new Coordinate(0,0)));
        vehicles.put(3, new Vehicle(3, true, new Coordinate(10,10)));

        when(vehicleDao.getAllVehicles()).thenReturn(vehicles);

        Vehicle actualResult = sut.getNearestVehicle(new Coordinate(12,12));
        Assert.assertEquals(null, actualResult);
    }

    private BookingDto getBooking(){
        return new BookingDto(new CoordinateDto(1,1), new CoordinateDto(10,10));
    }

}
