package com.kakao.payservice.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RegionDataDto {

    @ApiModelProperty(position = 0)
    private String region;

    @ApiModelProperty(position = 1)
    private String target;

    @ApiModelProperty(position = 2)
    private String usage;

    @ApiModelProperty(position = 3)
    private String limit;

    @ApiModelProperty(position = 4)
    private String rate;

    @ApiModelProperty(position = 5)
    private String institute;

    @ApiModelProperty(position = 6)
    private String mgmt;

    @ApiModelProperty(position = 7)
    private String reception;
}
