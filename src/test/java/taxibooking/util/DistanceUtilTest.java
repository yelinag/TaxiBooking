package taxibooking.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import taxibooking.bo.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class DistanceUtilTest {

    @Test
    public void testCalculateTimeDistance(){

        long[][] inputsOutputs = new long[4][5];
        //curX, curY, destX, destY, distance
        inputsOutputs[0] = new long[]{0, 0, 3, 1, 4};
        inputsOutputs[1] = new long[]{0, 0, -2147483648, -2147483648, 4294967296L};
        inputsOutputs[2] = new long[]{2147483647, 2147483647, 0, 0, 4294967294L};
        inputsOutputs[3] = new long[]{2147483647, 2147483647, -2147483648, -2147483648, 8589934590L};

        for(int i=0; i<inputsOutputs.length; i++){
            long actualResult = DistanceUtil.calculateTimeDistance(new Coordinate((int)inputsOutputs[i][0], (int)inputsOutputs[i][1]),
                    new Coordinate((int)inputsOutputs[i][2], (int)inputsOutputs[i][3]));
            Assert.assertEquals(inputsOutputs[i][4], actualResult);
        }

    }

}
