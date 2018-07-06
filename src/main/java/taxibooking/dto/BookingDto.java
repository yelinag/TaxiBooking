package taxibooking.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
@ApiModel(value="Booking Request Entity", description = "Request entity in order to perform taxi booking")
public class BookingDto {
    @ApiModelProperty(value="source position or customer location", required = true)
    @NonNull
    private CoordinateDto source;
    @ApiModelProperty(value="destination position", required = true)
    @NonNull
    private CoordinateDto destination;

}
