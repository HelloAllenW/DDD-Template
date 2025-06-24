package cn.helloallen.domain.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Data;

import java.util.Date;

@Data
public class ActivityParticipantDTO {

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

    /**
     * 创建人:创建人
     */
    @Schema(description = "创建人:创建人")
    private Integer createBy;

    /**
     * 创建时间:创建时间
     */
    @Schema(description = "创建时间:创建时间")
    private Date createTime;
}
