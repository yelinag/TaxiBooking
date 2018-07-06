package taxibooking.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
@ApiModel(value="Booked Response Entity", description = "Information about trip booked")
public class BookedDto {
    @ApiModelProperty(value="Vehicle ID that has been booked (Null if unable to book)",
        required = true)
    private Integer car_id;
    @ApiModelProperty(value="Total estimated time taken for whole trip (Null if unable to book)",
        required = true)
    private Long total_time;
}
