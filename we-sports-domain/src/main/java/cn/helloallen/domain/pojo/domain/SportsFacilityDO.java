package cn.helloallen.domain.pojo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@TableName("sports_facilities")
public class SportsFacilityDO {

    /**
     * 运动场所ID
     */
    @TableId(type = IdType.AUTO)
    @Schema(description = "运动场所ID")
    private Integer facilityId;

    /**
     * 运动场所名称
     */
    @Schema(description = "运动场所名称")
    private String name;

    /**
     * 运动场所地点
     */
    @Schema(description = "运动场所地点")
    private String location;

    /**
     * 运动场所描述
     */
    @Schema(description = "运动场所描述")
    private String description;

    /**
     * 创建人
     */
    @Schema(description = "创建人")
    private Integer createBy;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private Date createTime;

    /**
     * 修改人
     */
    @Schema(description = "修改人")
    private Integer updateBy;

    /**
     * 修改时间
     */
    @Schema(description = "修改时间")
    private Date updateTime;

    /**
     * 运动场所ID
     */
    @Schema(description = "运动场所ID")
    private Integer id;
}
