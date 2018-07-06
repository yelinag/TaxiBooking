package taxibooking.bo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TripTest {

    @Test
    public void testTickByOneWhenFetching(){
        Vehicle vehicle = new Vehicle(1, true, new Coordinate(-12,-12));
        Coordinate destination = new Coordinate(15,15);
        Customer customer = new Customer(new Coordinate(10, 10));
        Trip trip = new Trip(1, vehicle, destination, customer);
        int totalTickForFetch = 44;
        int totalTickForRide = 10;
        for(int i=0; i<totalTickForFetch; i++){
            Assert.assertEquals(TripState.FETCHING, trip.getState());
            trip.tickTimeByOne();
        }
        for(int i=0; i<totalTickForRide; i++){
            Assert.assertEquals(TripState.ON_TRIP, trip.getState());
            trip.tickTimeByOne();
        }
        Assert.assertEquals(TripState.ENDED, trip.getState());
    }

    @Test
    public void testTickByOneWhenOnTrip(){
        Vehicle vehicle = new Vehicle(1, true, new Coordinate(12,12));
        Coordinate destination = new Coordinate(15,15);
        Customer customer = new Customer(new Coordinate(10, 10));
        Trip trip = new Trip(1, vehicle, destination, customer);
        trip.setState(TripState.ON_TRIP);
        int totalTickForRide = 6;
        for(int i=0; i<totalTickForRide; i++){
            Assert.assertEquals(TripState.ON_TRIP, trip.getState());
            trip.tickTimeByOne();
        }
        Assert.assertEquals(TripState.ENDED, trip.getState());
    }

}
