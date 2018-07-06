package taxibooking.dao;

import org.springframework.stereotype.Component;
import taxibooking.bo.Coordinate;
import taxibooking.bo.Vehicle;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class VehicleDao {

    public static final int TOTAL_VEHICLE = 3;

    private Map<Integer, Vehicle> activeVehicles;
    private int latestVehicleId;

    @PostConstruct
    public void init() {
        this.initAllVehicles();
    }

    public Map<Integer, Vehicle> getAllVehicles(){
        return activeVehicles;
    }

    public synchronized void initAllVehicles(){
        activeVehicles = new HashMap<>();
        latestVehicleId = 0;
        for(int i=0; i<TOTAL_VEHICLE; i++){
            Vehicle newVehicle = insertNewVehicle();
            activeVehicles.put(newVehicle.getId(), newVehicle);
        }
    }

    public Vehicle insertNewVehicle(){
        Coordinate startingCoordinate = new Coordinate(0,0);
        synchronized (this) {
            Vehicle vehicle = new Vehicle(++latestVehicleId, false, startingCoordinate);
            activeVehicles.put(vehicle.getId(), vehicle);
            return vehicle;
        }
    }

}
