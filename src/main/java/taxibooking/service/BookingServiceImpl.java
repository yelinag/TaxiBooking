package taxibooking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taxibooking.dao.TripDao;
import taxibooking.dao.VehicleDao;
import taxibooking.dto.BookedDto;
import taxibooking.dto.BookingDto;
import taxibooking.bo.Coordinate;
import taxibooking.bo.Customer;
import taxibooking.bo.Trip;
import taxibooking.bo.Vehicle;
import taxibooking.mapper.CoordinateMapper;

import java.util.Map;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookingServiceImpl implements BookingService{

    private final VehicleDao vehicleDao;
    private final TripDao tripDao;

    public BookedDto bookTaxi(BookingDto requestBooking){
        Vehicle vehicle = getNearestVehicle(CoordinateMapper.convertToCoordinate(requestBooking.getSource()));
        if(vehicle==null) return new BookedDto(null, null);

        Coordinate destination = CoordinateMapper.convertToCoordinate(requestBooking.getDestination());
        Customer customer = new Customer(CoordinateMapper.convertToCoordinate(requestBooking.getSource()));
        Trip trip = tripDao.insertNewTrip(vehicle, destination, customer);
        return new BookedDto(vehicle.getId(), trip.calculateTotalEstimatedTime());
    }

    Vehicle getNearestVehicle(Coordinate customerPosition){
        long shortestDistance = Long.MAX_VALUE;
        Vehicle nearestVehicle = null;
        for(Map.Entry<Integer, Vehicle> vEntry: vehicleDao.getAllVehicles().entrySet()){
            Vehicle vehicle = vEntry.getValue();
            if(!vehicle.isOccupied()){
                long dist = vehicle.calculateTime(customerPosition);
                if(dist < shortestDistance || (dist==shortestDistance && vehicle.getId() < nearestVehicle.getId())) {
                    shortestDistance = dist;
                    nearestVehicle = vehicle;
                }
            }
        }
        return nearestVehicle;
    }

    public void initializeAll(){
        vehicleDao.initAllVehicles();
        tripDao.initAllTrips();
    }

}
