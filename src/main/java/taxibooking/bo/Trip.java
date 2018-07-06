package taxibooking.bo;

import lombok.Data;
import taxibooking.util.DistanceUtil;

@Data
public class Trip {

    private int id;
    private Vehicle vehicle;
    private TripState state;
    private Coordinate destination;
    private Customer customer;

    public Trip(int id, Vehicle vehicle, Coordinate destination, Customer customer){
        this.id = id;
        this.vehicle = vehicle;
        vehicle.book();
        this.destination = destination;
        this.customer = customer;
        this.state = TripState.FETCHING;
    }

    public long calculateTotalEstimatedTime(){
        return vehicle.calculateTime(customer.getStartingPosition()) +
                DistanceUtil.calculateTimeDistance(customer.getStartingPosition(),
                        destination);
    }

    public synchronized TripState tickTimeByOne(){
        int distX = 0;
        int distY = 0;
        if(TripState.FETCHING==state){
            distX = customer.getStartingPosition().getPosX() - vehicle.getCurrentPos().getPosX();
            distY = customer.getStartingPosition().getPosY() - vehicle.getCurrentPos().getPosY();
        }else if(TripState.ON_TRIP==state) {
            distX = destination.getPosX() - vehicle.getCurrentPos().getPosX();
            distY = destination.getPosY() - vehicle.getCurrentPos().getPosY();
        }

        if(distX > 0) vehicle.getCurrentPos().offsetX(1);
        else if(distX < 0) vehicle.getCurrentPos().offsetX(-1);

        if(distX==0) {
            if (distY > 0) vehicle.getCurrentPos().offsetY(1);
            else if (distY < 0) vehicle.getCurrentPos().offsetY(-1);
        }

        if(hasReachedToCustomer()){
            fetchedCustomer();
        }
        if(hasReachedDestination()){
            endTrip();
        }
        return state;
    }

    public synchronized boolean hasReachedToCustomer(){
        if(TripState.FETCHING==state && customer.getStartingPosition().getPosX() - vehicle.getCurrentPos().getPosX() ==0 &&
                customer.getStartingPosition().getPosY() - vehicle.getCurrentPos().getPosY() == 0)
            return true;
        return false;
    }

    public synchronized boolean hasReachedDestination(){
        if(TripState.ON_TRIP==state && destination.getPosX() - vehicle.getCurrentPos().getPosX() == 0 &&
                destination.getPosY() - vehicle.getCurrentPos().getPosY()== 0)
            return true;
        return false;
    }

    private synchronized void endTrip(){
        state = TripState.ENDED;
        vehicle.setFree();
    }

    private synchronized void fetchedCustomer(){
        state = TripState.ON_TRIP;
    }
}
