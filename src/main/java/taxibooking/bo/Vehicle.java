package taxibooking.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import taxibooking.util.DistanceUtil;

@Data
@AllArgsConstructor
public class Vehicle {

    private int id;
    private boolean occupied;
    @NonNull
    private Coordinate currentPos;

    public long calculateTime(Coordinate destination){
        return DistanceUtil.calculateTimeDistance(currentPos, destination);
    }

    public void setFree(){
        this.occupied = false;
    }

    public void book(){
        occupied = true;
    }
}
