package cn.helloallen.domain.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    /**
     * 用户ID:更新和删除用户时必填
     */
    @NotNull(groups = { UpdateGroup.class, DeleteGroup.class }, message = "用户ID不能为空")
    @Schema(description = "用户ID:更新和删除用户时必填")
    private Integer userId;

    /**
     * 用户名:创建用户时必填
     */
    @NotBlank(groups = { CreateGroup.class }, message = "用户名不能为空")
    @Schema(description = "用户名:创建用户时必填")
    private String username;

    /**
     * 微信OpenID:创建用户时可选
     */
    @Schema(description = "微信OpenID:创建用户时可选")
    private String wechatOpenid;

    /**
     * 手机号码:创建用户时可选
     */
    @Schema(description = "手机号码:创建用户时可选")
    private String phoneNumber;
}
