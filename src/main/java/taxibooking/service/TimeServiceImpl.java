package taxibooking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taxibooking.dao.TripDao;
import taxibooking.bo.Trip;

import java.util.Map;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TimeServiceImpl implements TimeService{

    private final TripDao tripDao;

    public void tickTimeByOne(){
        Map<Integer, Trip> trips = tripDao.getAllOnGoingTrips();
        for(Map.Entry<Integer, Trip> tripEntry: trips.entrySet()){
            tripEntry.getValue().tickTimeByOne();
        }
    }
}
