package cn.helloallen.domain.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class LocationVO {

    /**
     * 纬度:纬度
     */
    @Schema(description = "纬度:纬度")
    private String latitude;

    /**
     * 经度:经度
     */
    @Schema(description = "经度:经度")
    private String longitude;
}
