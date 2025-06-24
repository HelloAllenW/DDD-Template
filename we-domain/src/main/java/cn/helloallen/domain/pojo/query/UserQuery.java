package cn.helloallen.domain.pojo.query;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserQuery {

    /**
     * 用户ID:用户id，必填
     */
    @NotNull(groups = { QueryGroup.class }, message = "用户ID不能为空")
    @Schema(description = "用户ID:用户id，必填")
    private Integer userId;

    /**
     * 活动ID:活动id，必填
     */
    @NotNull(groups = { QueryGroup.class }, message = "活动ID不能为空")
    @Schema(description = "活动ID:活动id，必填")
    private Integer activityId;

    /**
     * 用户位置信息:用户位置信息，必填
     */
    @NotBlank(groups = { QueryGroup.class }, message = "用户位置信息不能为空")
    @Schema(description = "用户位置信息:用户位置信息，必填")
    private String location;

    /**
     * 检索半径:检索半径，必填
     */
    @NotNull(groups = { QueryGroup.class }, message = "检索半径不能为空")
    @Schema(description = "检索半径:检索半径，必填")
    private Integer radius;

    /**
     * 分页参数:页码
     */
    @Schema(description = "分页参数:页码")
    private Integer pageIndex = 1;

    /**
     * 分页参数:每页大小
     */
    @Schema(description = "分页参数:每页大小")
    private Integer pageSize = 10;
}
