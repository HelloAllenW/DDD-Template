package cn.helloallen.domain.pojo.domain;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@TableName("activities")
public class ActivityDO {
    /**
     * 活动ID:活动ID
     */
    @TableId(type = IdType.AUTO)
    @Schema(description = "活动ID:活动ID")
    private Integer activityId;

    /**
     * 活动发起人用户ID:活动发起人用户ID
     */
    @Schema(description = "活动发起人用户ID:活动发起人用户ID")
    private Integer userId;

    /**
     * 活动标题:活动标题
     */
    @Schema(description = "活动标题:活动标题")
    private String title;

    /**
     * 活动内容:活动内容
     */
    @Schema(description = "活动内容:活动内容")
    private String content;

    /**
     * 活动地点:活动地点
     */
    @Schema(description = "活动地点:活动地点")
    private String location;

    /**
     * 活动类型:活动类型
     */
    @Schema(description = "活动类型:活动类型")
    private String type;

    /**
     * 活动发起时间:活动发起时间
     */
    @Schema(description = "活动发起时间:活动发起时间")
    private Date startTime;

    /**
     * 最大参加人数:最大参加人数
     */
    @Schema(description = "最大参加人数:最大参加人数")
    private Integer maxParticipants;

    /**
     * 人均消费:人均消费
     */
    @Schema(description = "人均消费:人均消费")
    private Double averageConsumption;

    /**
     * 联系人信息:联系人信息
     */
    @Schema(description = "联系人信息:联系人信息")
    private String contactInfo;

    /**
     * 活动大图URL:活动大图URL
     */
    @Schema(description = "活动大图URL:活动大图URL")
    private String mainImageUrl;

    /**
     * 活动详情图片URL列表:活动详情图片URL列表
     */
    @Schema(description = "活动详情图片URL列表:活动详情图片URL列表")
    private String detailImageUrls;

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
