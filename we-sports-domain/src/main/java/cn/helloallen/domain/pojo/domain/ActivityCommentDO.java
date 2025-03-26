package cn.helloallen.domain.pojo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@TableName("activity_comments")
public class ActivityCommentDO {

    /**
     * 评论ID:评论ID
     */
    @TableId(type = IdType.AUTO)
    @Schema(description = "评论ID:评论ID")
    private Integer commentId;

    /**
     * 活动ID:活动ID
     */
    @Schema(description = "活动ID:活动ID")
    private Integer activityId;

    /**
     * 评论用户ID:评论用户ID
     */
    @Schema(description = "评论用户ID:评论用户ID")
    private Integer userId;

    /**
     * 评论内容:评论内容
     */
    @Schema(description = "评论内容:评论内容")
    private String content;

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
