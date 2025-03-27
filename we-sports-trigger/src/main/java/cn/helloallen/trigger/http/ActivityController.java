package cn.helloallen.trigger.http;

import cn.helloallen.domain.constant.ResultCodeConstant;
import cn.helloallen.domain.pojo.domain.ActivityDO;
import cn.helloallen.domain.pojo.domain.SportsFacilityDO;
import cn.helloallen.domain.pojo.dto.*;
import cn.helloallen.domain.pojo.query.ActivityQuery;
import cn.helloallen.domain.pojo.query.LocationQuery;
import cn.helloallen.domain.pojo.query.QueryGroup;
import cn.helloallen.domain.pojo.vo.PageResult;
import cn.helloallen.domain.service.ActivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "活动详情管理")
@RequestMapping("activity")
@RestController
@Validated // 启用方法级验证
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    /**
     * 查看活动详细信息
     *
     * @param activityQuery 活动查询入参
     * @return
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @Operation(summary = "查看活动详细信息")
    public RestResult<ActivityDO> activityInfo(@Validated(QueryGroup.class) ActivityQuery activityQuery) {
        ActivityDO result = activityService.activityInfo(activityQuery);
        return new RestResult<>(ResultCodeConstant.CODE_000000, ResultCodeConstant.CODE_000000_MSG, result);
    }

    /**
     * 对活动点赞
     *
     * @param activityLikeDTO 活动点赞对象
     * @return
     */
    @RequestMapping(value = "/like", method = RequestMethod.POST)
    @Operation(summary = "对活动点赞")
    @ResponseBody
    public RestResult<Boolean> likeActivity(@RequestBody @Validated(CreateGroup.class) ActivityLikeDTO activityLikeDTO) {
        Boolean result = activityService.likeActivity(activityLikeDTO);
        return new RestResult<>(ResultCodeConstant.CODE_000000, ResultCodeConstant.CODE_000000_MSG, result);
    }

    /**
     * 对活动评论
     *
     * @param activityCommentDTO 活动评论对象
     * @return
     */
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    @Operation(summary = "对活动评论")
    @ResponseBody
    public RestResult<Boolean> commentActivity(@RequestBody @Validated(CreateGroup.class) ActivityCommentDTO activityCommentDTO) {
        Boolean result = activityService.commentActivity(activityCommentDTO);
        return new RestResult<>(ResultCodeConstant.CODE_000000, ResultCodeConstant.CODE_000000_MSG, result);
    }

    /**
     * 申请参与活动
     *
     * @param activityParticipantDTO 活动参与者对象
     * @return
     */
    @RequestMapping(value = "/participant", method = RequestMethod.POST)
    @Operation(summary = "申请参与活动")
    @ResponseBody
    public RestResult<Boolean> applyActivity(@RequestBody @Validated(CreateGroup.class) ActivityParticipantDTO activityParticipantDTO) {
        Boolean result = activityService.applyActivity(activityParticipantDTO);
        return new RestResult<>(ResultCodeConstant.CODE_000000, ResultCodeConstant.CODE_000000_MSG, result);
    }

    /**
     * 查找周边运动场所
     *
     * @param locationQuery 地理位置查询对象
     * @return
     */
    @RequestMapping(value = "/nearbyFacilities", method = RequestMethod.GET)
    @Operation(summary = "查找周边运动场所")
    public RestResult<PageResult<SportsFacilityDO>> findNearbyFacilities(@Validated(QueryGroup.class) LocationQuery locationQuery) {
        PageResult<SportsFacilityDO> result = activityService.findNearbyFacilities(locationQuery);
        return new RestResult<>(ResultCodeConstant.CODE_000000, ResultCodeConstant.CODE_000000_MSG, result);
    }

    /**
     * 发起活动
     *
     * @param activityDTO 活动信息对象
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @Operation(summary = "发起活动")
    @ResponseBody
    public RestResult<Boolean> addActivity(@RequestBody @Validated(CreateGroup.class) ActivityDTO activityDTO) {
        log.info("测试测试");
        Boolean result = activityService.addActivity(activityDTO);
        return new RestResult<>(ResultCodeConstant.CODE_000000, ResultCodeConstant.CODE_000000_MSG, result);
    }
}
