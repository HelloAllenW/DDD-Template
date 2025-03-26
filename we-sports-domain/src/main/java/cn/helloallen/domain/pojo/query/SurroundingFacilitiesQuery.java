package cn.helloallen.domain.pojo.query;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Data;

@Data
public class SurroundingFacilitiesQuery {

    /**
     * 纬度
     */
    @NotNull(groups = { QueryGroup.class, Default.class }, message = "纬度不能为空")
    @Schema(description = "纬度")
    private Double latitude;

    /**
     * 经度
     */
    @NotNull(groups = { QueryGroup.class, Default.class }, message = "经度不能为空")
    @Schema(description = "经度")
    private Double longitude;

    /**
     * 半径
     */
    @Schema(description = "半径")
    private Integer radius;
}
