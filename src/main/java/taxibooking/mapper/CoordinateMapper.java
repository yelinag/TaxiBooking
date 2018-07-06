package taxibooking.mapper;

import taxibooking.dto.CoordinateDto;
import taxibooking.bo.Coordinate;

public class CoordinateMapper {

    public static Coordinate convertToCoordinate(CoordinateDto coordinateDto){
        return new Coordinate(coordinateDto.getX(), coordinateDto.getY());
    }

    public static CoordinateDto convertToCoordinateDto(Coordinate coordinate){
        return new CoordinateDto(coordinate.getPosX(), coordinate.getPosY());
    }
}
