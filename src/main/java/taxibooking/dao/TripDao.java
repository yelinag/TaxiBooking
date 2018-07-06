package taxibooking.dao;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import taxibooking.bo.Coordinate;
import taxibooking.bo.Customer;
import taxibooking.bo.Trip;
import taxibooking.bo.Vehicle;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class TripDao {

    private Map<Integer, Trip> onGoingtrips;
    private int latestTripId;

    @PostConstruct
    public void init() {
        this.initAllTrips();
    }

    public Map<Integer, Trip> getAllOnGoingTrips(){
        return onGoingtrips;
    }

    public synchronized void initAllTrips(){
        onGoingtrips = new HashMap<>();
        latestTripId = 0;
    }

    public synchronized Trip insertNewTrip(@NonNull Vehicle vehicle, @NonNull Coordinate destination, @NonNull Customer customer){
        Trip trip = new Trip(++latestTripId, vehicle, destination, customer);
        onGoingtrips.put(trip.getId(), trip);
        return trip;
    }


}
