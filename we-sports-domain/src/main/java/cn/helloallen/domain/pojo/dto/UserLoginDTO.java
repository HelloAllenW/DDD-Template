package cn.helloallen.domain.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLoginDTO {

    /**
     * 微信OpenID:微信登录时必填
     */
    @NotBlank(groups = { CreateGroup.class }, message = "微信OpenID不能为空")
    @Schema(description = "微信OpenID:微信登录时必填")
    private String wechatOpenid;

    /**
     * 手机号码:手机号登录时必填
     */
    @NotBlank(groups = { CreateGroup.class }, message = "手机号码不能为空")
    @Schema(description = "手机号码:手机号登录时必填")
    private String phoneNumber;
}
