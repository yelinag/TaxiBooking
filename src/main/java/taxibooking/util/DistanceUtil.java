package taxibooking.util;

import taxibooking.bo.Coordinate;

public class DistanceUtil {
    public static long calculateTimeDistance(Coordinate curPos, Coordinate destPost){
        return Math.abs((long)destPost.getPosX() - (long)curPos.getPosX()) +
                Math.abs((long)destPost.getPosY() - (long)curPos.getPosY());
    }
}
