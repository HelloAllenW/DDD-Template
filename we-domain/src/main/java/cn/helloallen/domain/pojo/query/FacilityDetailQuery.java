package cn.helloallen.domain.pojo.query;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Data;

@Data
public class FacilityDetailQuery {

    /**
     * 运动场所ID
     */
    @NotNull(groups = { QueryGroup.class, Default.class }, message = "运动场所ID不能为空")
    @Schema(description = "运动场所ID")
    private Integer facilityId;
}
