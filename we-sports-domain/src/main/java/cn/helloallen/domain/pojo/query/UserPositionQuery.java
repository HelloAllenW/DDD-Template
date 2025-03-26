package cn.helloallen.domain.pojo.query;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Data;

@Data
public class UserPositionQuery {

    /**
     * 用户ID
     */
    @NotNull(groups = { QueryGroup.class, Default.class }, message = "用户ID不能为空")
    @Schema(description = "用户ID")
    private Integer userId;
}
