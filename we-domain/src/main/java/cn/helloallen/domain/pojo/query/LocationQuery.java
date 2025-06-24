package cn.helloallen.domain.pojo.query;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LocationQuery {

    /**
     * 纬度
     */
    @NotNull(groups = { QueryGroup.class }, message = "纬度不能为空")
    @Schema(description = "纬度")
    private Double latitude;

    /**
     * 经度
     */
    @NotNull(groups = { QueryGroup.class }, message = "经度不能为空")
    @Schema(description = "经度")
    private Double longitude;
}
