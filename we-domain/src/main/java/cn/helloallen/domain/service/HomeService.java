package cn.helloallen.domain.service;

import cn.helloallen.domain.pojo.domain.UserDO;
import cn.helloallen.domain.pojo.query.ActivityQuery;
import cn.helloallen.domain.pojo.query.UserLocationQuery;
import cn.helloallen.domain.pojo.query.UserQuery;
import cn.helloallen.domain.pojo.vo.ActivityVO;
import cn.helloallen.domain.pojo.vo.LocationVO;
import cn.helloallen.domain.pojo.vo.PageResult;

import java.util.List;

public interface HomeService {
    /**
     * user-location
     *
     * @param userQuery
     * @return
     */
    LocationVO getUserLocation(UserQuery userQuery);

    /**
     * nearby-facilities
     *
     * @param userLocationQuery
     * @return
     */
    List<ActivityVO> getNearbyFacilities(UserLocationQuery userLocationQuery);

    /**
     * search-activities
     *
     * @param activityQuery 活动查询入参
     * @return
     */
    PageResult<ActivityVO> searchActivities(ActivityQuery activityQuery);

    /**
     * activity-details
     *
     * @param activityQuery 活动查询入参
     * @return
     */
    ActivityVO getActivityDetails(ActivityQuery activityQuery);

    /**
     * activity-organizer
     *
     * @param activityQuery 活动查询入参
     * @return  用户实体
     */
    UserDO getActivityOrganizer(ActivityQuery activityQuery);

    /**
     * activity-images
     *
     * @param activityQuery 活动查询入参
     * @return
     */
    String[] getActivityImages(ActivityQuery activityQuery);

}
