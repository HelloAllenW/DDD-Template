package cn.helloallen.trigger.http;
import cn.helloallen.domain.constant.ResultCodeConstant;
import cn.helloallen.domain.pojo.domain.UserDO;
import cn.helloallen.domain.pojo.dto.RestResult;
import cn.helloallen.domain.pojo.query.ActivityQuery;
import cn.helloallen.domain.pojo.query.QueryGroup;
import cn.helloallen.domain.pojo.query.UserLocationQuery;
import cn.helloallen.domain.pojo.query.UserQuery;
import cn.helloallen.domain.pojo.vo.ActivityVO;
import cn.helloallen.domain.pojo.vo.LocationVO;
import cn.helloallen.domain.pojo.vo.PageResult;
import cn.helloallen.domain.service.HomeService;
import cn.helloallen.types.common.Constants;
import cn.helloallen.types.model.Response;
import com.sun.deploy.services.DefaultService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.annotation.Resource;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadPoolExecutor;

@Tag(name = "活动首页管理")
@RequestMapping("home")
@Slf4j
@RestController
public class HomeController {
    @Autowired
    private HomeService defaultService;

    /**
     * user-location
     *
     * @param userQuery
     * @return
     */
    @RequestMapping(value = "/user-location", method = RequestMethod.GET)
    @Operation(summary = "user-location")
    public RestResult<LocationVO> getUserLocation(@Validated(QueryGroup.class) UserQuery userQuery) {
        LocationVO result = defaultService.getUserLocation(userQuery);
        return new RestResult<>(ResultCodeConstant.CODE_000000, ResultCodeConstant.CODE_000000_MSG, result);
    }

    /**
     * nearby-facilities
     *
     * @param userLocationQuery
     * @return
     */
    @RequestMapping(value = "/nearby-facilities", method = RequestMethod.GET)
    @Operation(summary = "nearby-facilities")
    public RestResult<List<ActivityVO>> getNearbyFacilities(@Validated(QueryGroup.class) UserLocationQuery userLocationQuery) {
        List<ActivityVO> result = defaultService.getNearbyFacilities(userLocationQuery);
        return new RestResult<>(ResultCodeConstant.CODE_000000, ResultCodeConstant.CODE_000000_MSG, result);
    }

    /**
     * search-activities
     *
     * @param activityQuery 活动查询入参
     * @return
     */
    @RequestMapping(value = "/search-activities", method = RequestMethod.GET)
    @Operation(summary = "search-activities")
    public RestResult<PageResult<ActivityVO>> searchActivities(@Validated(QueryGroup.class) ActivityQuery activityQuery) {
        PageResult<ActivityVO> result = defaultService.searchActivities(activityQuery);
        return new RestResult<>(ResultCodeConstant.CODE_000000, ResultCodeConstant.CODE_000000_MSG, result);
    }

    /**
     * activity-details
     *
     * @param activityQuery 活动查询入参
     * @return
     */
    @RequestMapping(value = "/activity-details", method = RequestMethod.GET)
    @Operation(summary = "activity-details")
    public RestResult<ActivityVO> getActivityDetails(@Validated(QueryGroup.class) ActivityQuery activityQuery) {
        ActivityVO result = defaultService.getActivityDetails(activityQuery);
        return new RestResult<>(ResultCodeConstant.CODE_000000, ResultCodeConstant.CODE_000000_MSG, result);
    }

    /**
     * activity-organizer
     *
     * @param activityQuery 活动查询入参
     * @return
     */
    @RequestMapping(value = "/activity-organizer", method = RequestMethod.GET)
    @Operation(summary = "activity-organizer")
    public RestResult<UserDO> getActivityOrganizer(@Validated(QueryGroup.class) ActivityQuery activityQuery) {
        UserDO result = defaultService.getActivityOrganizer(activityQuery);
        return new RestResult<>(ResultCodeConstant.CODE_000000, ResultCodeConstant.CODE_000000_MSG, result);
    }

    /**
     * activity-images
     *
     * @param activityQuery 活动查询入参
     * @return
     */
    @RequestMapping(value = "/activity-images", method = RequestMethod.GET)
    @Operation(summary = "activity-images")
    public RestResult<String[]> getActivityImages(@Validated(QueryGroup.class) ActivityQuery activityQuery) {
        String[] result = defaultService.getActivityImages(activityQuery);
        return new RestResult<>(ResultCodeConstant.CODE_000000, ResultCodeConstant.CODE_000000_MSG, result);
    }
}
