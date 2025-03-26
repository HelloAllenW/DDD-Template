package cn.helloallen.domain.dao;

import cn.helloallen.domain.pojo.domain.ActivityCommentDO;
import cn.helloallen.domain.pojo.domain.ActivityDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ActivityMapper extends BaseMapper<ActivityDO> {

    // 根据活动ID查询活动详情:根据活动ID查询活动详情信息
    @Select("SELECT activity_id, title, content, location, type, start_time, max_participants, average_consumption, contact_info, main_image_url, detail_image_urls FROM activities WHERE activity_id = #{activityId}")
    ActivityDO selectById(@Param("activityId") Integer activityId);

    /**
     * 保存评论记录
     * @Param commentDO 评论实体对象
     * @Return 返回插入结果
     */
    int insertComment(@Param("commentDO") ActivityCommentDO commentDO);
}
