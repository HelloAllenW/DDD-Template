package cn.helloallen.domain.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import jakarta.validation.constraints.NotBlank;
import java.util.Date;
import cn.helloallen.domain.pojo.domain.ActivityCommentDO;

@Data
public class ActivityCommentDTO {

    /**
     * 用户ID:用户ID，必填
     */
    @NotNull(groups = { CreateGroup.class, Default.class }, message = "用户ID不能为空")
    @Schema(description = "用户ID:用户ID，必填")
    private Integer userId;

    /**
     * 活动ID:活动ID，必填
     */
    @NotNull(groups = { CreateGroup.class, Default.class }, message = "活动ID不能为空")
    @Schema(description = "活动ID:活动ID，必填")
    private Integer activityId;

    /**
     * 评论内容:评论内容，必填
     */
    @NotBlank(groups = { CreateGroup.class, Default.class }, message = "评论内容不能为空")
    @Schema(description = "评论内容:评论内容，必填")
    private String content;

    /**
     * 转换为ActivityCommentDO对象
     * @Return ActivityCommentDO对象
     */
    public ActivityCommentDO toActivityCommentDO() {
        ActivityCommentDO commentDO = new ActivityCommentDO();
        commentDO.setActivityId(this.activityId);
        commentDO.setUserId(this.userId);
        commentDO.setContent(this.content);
        commentDO.setCreateBy(this.userId);
        commentDO.setCreateTime(new Date());
        return commentDO;
    }
}