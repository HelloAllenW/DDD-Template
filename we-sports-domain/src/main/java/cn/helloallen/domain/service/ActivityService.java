package cn.helloallen.domain.service;

import cn.helloallen.domain.pojo.domain.ActivityDO;
import cn.helloallen.domain.pojo.domain.SportsFacilityDO;
import cn.helloallen.domain.pojo.dto.ActivityCommentDTO;
import cn.helloallen.domain.pojo.dto.ActivityDTO;
import cn.helloallen.domain.pojo.dto.ActivityLikeDTO;
import cn.helloallen.domain.pojo.dto.ActivityParticipantDTO;
import cn.helloallen.domain.pojo.query.ActivityQuery;
import cn.helloallen.domain.pojo.query.LocationQuery;
import cn.helloallen.domain.pojo.vo.PageResult;

public interface ActivityService {
    /**
     * 查看活动详细信息
     *
     * @param activityQuery 活动查询入参
     * @return
     */
    ActivityDO activityInfo(ActivityQuery activityQuery);

    /**
     * 对活动点赞
     *
     * @param activityLikeDTO 活动点赞对象
     * @return
     */
    Boolean likeActivity(ActivityLikeDTO activityLikeDTO);

    /**
     * 对活动评论
     *
     * @param activityCommentDTO 活动评论对象
     * @return
     */
    Boolean commentActivity(ActivityCommentDTO activityCommentDTO);

    /**
     * 申请参与活动
     *
     * @param activityParticipantDTO 活动参与者对象
     * @return
     */
    Boolean applyActivity(ActivityParticipantDTO activityParticipantDTO);

    /**
     * 查找周边运动场所
     *
     * @param locationQuery 地理位置查询对象
     * @return
     */
    PageResult<SportsFacilityDO> findNearbyFacilities(LocationQuery locationQuery);

    /**
     * 发起活动
     *
     * @param activityDTO 活动信息对象
     * @return
     */
    Boolean addActivity(ActivityDTO activityDTO);
}
