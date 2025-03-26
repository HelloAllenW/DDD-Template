package cn.helloallen.domain.dao;

import cn.helloallen.domain.pojo.domain.ActivityParticipantDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ActivityParticipantsMapper extends BaseMapper<ActivityParticipantDO> {

    /**
     * 根据用户ID和活动ID查询参与记录
     * @Param userId 用户ID
     * @Param activityId 活动ID
     * @Return 返回参与记录
     */
    ActivityParticipantDO selectByUserIdAndActivityId(@Param("userId") Integer userId, @Param("activityId") Integer activityId);

    /**
     * 根据活动ID查询参与人数
     * @Param activityId 活动ID
     * @Return 返回参与人数
     */
    @Select("SELECT COUNT(participant_id) FROM activity_participants WHERE activity_id = #{activityId}")
    long selectCountByActivityId(@Param("activityId") Integer activityId);
}
