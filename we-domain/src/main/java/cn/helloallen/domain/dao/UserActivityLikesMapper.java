package cn.helloallen.domain.dao;

import cn.helloallen.domain.pojo.domain.UserActivityLikeDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface UserActivityLikesMapper extends BaseMapper<UserActivityLikeDO> {

    /**
     * 根据用户ID和活动ID查询点赞记录
     * @Param userId 用户ID
     * @Param activityId 活动ID
     * @Return 返回点赞记录
     */
    UserActivityLikeDO selectByUserIdAndActivityId(@Param("userId") Integer userId, @Param("activityId") Integer activityId);
}
