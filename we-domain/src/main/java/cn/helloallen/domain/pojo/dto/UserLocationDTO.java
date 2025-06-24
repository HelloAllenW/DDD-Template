package cn.helloallen.domain.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLocationDTO {

    /**
     * 用户ID
     */
    @NotNull(groups = { CreateGroup.class, Default.class }, message = "用户ID不能为空")
    @Schema(description = "用户ID")
    private Integer userId;

    /**
     * 纬度
     */
    @NotNull(groups = { CreateGroup.class, Default.class }, message = "纬度不能为空")
    @Schema(description = "纬度")
    private Double latitude;

    /**
     * 经度
     */
    @NotNull(groups = { CreateGroup.class, Default.class }, message = "经度不能为空")
    @Schema(description = "经度")
    private Double longitude;

    /**
     * 微信OpenID
     */
    @Schema(description = "微信OpenID")
    private String wechatOpenid;

    /**
     * 手机号码
     */
    @Schema(description = "手机号码")
    private String phoneNumber;
}
