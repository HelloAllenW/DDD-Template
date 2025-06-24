package cn.helloallen.domain.pojo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@TableName("activity_participants")
public class ActivityParticipantDO {

    /**
     * 参与记录ID:参与记录ID
     */
    @TableId(type = IdType.AUTO)
    @Schema(description = "参与记录ID:参与记录ID")
    private Integer participantId;

    /**
     * 活动ID:活动ID
     */
    @Schema(description = "活动ID:活动ID")
    private Integer activityId;

    /**
     * 参与用户ID:参与用户ID
     */
    @Schema(description = "参与用户ID:参与用户ID")
    private Integer userId;

    /**
     * 参与状态:参与状态
     */
    @Schema(description = "参与状态:参与状态")
    private String status;

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
}
