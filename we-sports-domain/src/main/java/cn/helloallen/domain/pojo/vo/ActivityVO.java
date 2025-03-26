package cn.helloallen.domain.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
public class ActivityVO {

    /**
     * 活动ID:活动ID
     */
    @Schema(description = "活动ID:活动ID")
    private Integer activityId;

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
    private String[] detailImageUrls;
}
