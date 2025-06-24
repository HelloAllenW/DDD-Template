package cn.helloallen.domain.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class ActivityDTO {

    /**
     * 用户ID
     */
    @NotNull(groups = { CreateGroup.class }, message = "用户ID不能为空")
    @Schema(description = "用户ID")
    private Integer userId;

    /**
     * 活动标题
     */
    @NotBlank(groups = { CreateGroup.class }, message = "活动标题不能为空")
    @Schema(description = "活动标题")
    private String title;

    /**
     * 活动内容
     */
    @NotBlank(groups = { CreateGroup.class }, message = "活动内容不能为空")
    @Schema(description = "活动内容")
    private String content;

    /**
     * 活动地点
     */
    @NotBlank(groups = { CreateGroup.class }, message = "活动地点不能为空")
    @Schema(description = "活动地点")
    private String location;

    /**
     * 活动类型
     */
    @NotBlank(groups = { CreateGroup.class }, message = "活动类型不能为空")
    @Schema(description = "活动类型")
    private String type;

    /**
     * 活动发起时间
     */
    @NotNull(groups = { CreateGroup.class }, message = "活动发起时间不能为空")
    @Schema(description = "活动发起时间")
    private Date startTime;

    /**
     * 最大参加人数
     */
    @Min(value = 0, groups = { CreateGroup.class }, message = "最大参加人数不能为负数")
    @Schema(description = "最大参加人数")
    private Integer maxParticipants;

    /**
     * 人均消费
     */
    @Schema(description = "人均消费")
    private Double averageConsumption;

    /**
     * 联系人信息
     */
    @Schema(description = "联系人信息")
    private String contactInfo;

    /**
     * 活动大图URL
     */
    @Schema(description = "活动大图URL")
    private String mainImageUrl;

    /**
     * 活动详情图片URL列表
     */
    @Schema(description = "活动详情图片URL列表")
    private String detailImageUrls;
}
