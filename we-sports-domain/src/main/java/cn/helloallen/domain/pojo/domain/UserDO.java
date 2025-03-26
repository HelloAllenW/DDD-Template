package cn.helloallen.domain.pojo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("users")
public class UserDO {

    /**
     * 用户ID:用户ID
     */
    @TableId(type = IdType.AUTO)
    @Schema(description = "用户ID:用户ID")
    private Integer userId;

    /**
     * 用户名:用户名
     */
    @Schema(description = "用户名:用户名")
    private String username;

    /**
     * 微信OpenID:微信OpenID
     */
    @Schema(description = "微信OpenID:微信OpenID")
    private String wechatOpenid;

    /**
     * 手机号码:手机号码
     */
    @Schema(description = "手机号码:手机号码")
    private String phoneNumber;

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

    /**
     * 修改人:修改人
     */
    @Schema(description = "修改人:修改人")
    private Integer updateBy;

    /**
     * 修改时间:修改时间
     */
    @Schema(description = "修改时间:修改时间")
    private Date updateTime;

    /**
     * 用户ID
     */
    @TableId(type = IdType.AUTO)
    @Schema(description = "用户ID")
    private Integer id;

    /**
     * 位置信息（纬度,经度）
     */
    @Schema(description = "位置信息（纬度,经度）")
    private String location;
}
