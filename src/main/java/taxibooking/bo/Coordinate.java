package taxibooking.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@AllArgsConstructor
@Data
public class Coordinate {
    @NonNull
    private int posX;
    @NonNull
    private int posY;

    public void offsetX(int value){
        posX += value;
    }

    public void offsetY(int value){
        posY += value;
    }
}
