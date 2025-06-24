package cn.helloallen.domain.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Data;

@Data
public class ActivityLikeDTO {

    /**
     * 用户ID:用户ID，必填
     */
    @NotNull(groups = { CreateGroup.class, Default.class }, message = "用户ID不能为空")
    @Schema(description = "用户ID:用户ID，必填")
    private Integer userId;

    /**
     * 活动ID:活动ID，必填
     */
    @NotNull(groups = { CreateGroup.class, Default.class }, message = "活动ID不能为空")
    @Schema(description = "活动ID:活动ID，必填")
    private Integer activityId;
}
