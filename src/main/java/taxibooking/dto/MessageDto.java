package taxibooking.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ApiModel(value = "Message Response Entity", description = "Information for operation being performed")
public class MessageDto {
    @ApiModelProperty(value="operation status message (success or fail)", required = true)
    private String message;
}
