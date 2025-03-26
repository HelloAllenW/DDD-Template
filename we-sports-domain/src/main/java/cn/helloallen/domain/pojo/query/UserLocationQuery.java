package cn.helloallen.domain.pojo.query;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserLocationQuery {

    /**
     * 用户ID:用户id，必填
     */
    @NotNull(groups = { QueryGroup.class }, message = "用户ID不能为空")
    @Schema(description = "用户ID:用户id，必填")
    private Integer userId;

    /**
     * 用户位置信息:用户位置信息，必填
     */
    @NotBlank(groups = { QueryGroup.class }, message = "用户位置信息不能为空")
    @Schema(description = "用户位置信息:用户位置信息，必填")
    private String location;
}
