package cn.helloallen.trigger.http;

import cn.helloallen.domain.constant.ResultCodeConstant;
import cn.helloallen.domain.pojo.domain.ActivityDO;
import cn.helloallen.domain.pojo.domain.ActivityParticipantDO;
import cn.helloallen.domain.pojo.domain.SportsFacilityDO;
import cn.helloallen.domain.pojo.domain.UserDO;
import cn.helloallen.domain.pojo.dto.*;
import cn.helloallen.domain.pojo.query.*;
import cn.helloallen.domain.pojo.vo.PageResult;
import cn.helloallen.domain.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "用户信息管理")
@RequestMapping("user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取用户登录信息
     *
     * @param userQuery
     * @return
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @Operation(summary = "获取用户登录信息")
    public RestResult<UserDO> userInfo(@Validated(QueryGroup.class) UserQuery userQuery) {
        UserDO result = userService.userInfo(userQuery);
        return new RestResult<>(ResultCodeConstant.CODE_000000, ResultCodeConstant.CODE_000000_MSG, result);
    }

    /**
     * 获取用户发起的活动列表
     *
     * @param userQuery
     * @return
     */
    @RequestMapping(value = "/activities/initiated", method = RequestMethod.GET)
    @Operation(summary = "获取用户发起的活动列表")
    public RestResult<PageResult<ActivityDO>> userInitiatedActivities(@Validated(QueryGroup.class) UserQuery userQuery) {
        PageResult<ActivityDO> result = userService.userInitiatedActivities(userQuery);
        return new RestResult<>(ResultCodeConstant.CODE_000000, ResultCodeConstant.CODE_000000_MSG, result);
    }

    /**
     * 获取用户参与的活动列表
     *
     * @param userQuery
     * @return
     */
    @RequestMapping(value = "/activities/participated", method = RequestMethod.GET)
    @Operation(summary = "获取用户参与的活动列表")
    public RestResult<PageResult<ActivityDO>> userParticipatedActivities(@Validated(QueryGroup.class) UserQuery userQuery) {
        PageResult<ActivityDO> result = userService.userParticipatedActivities(userQuery);
        return new RestResult<>(ResultCodeConstant.CODE_000000, ResultCodeConstant.CODE_000000_MSG, result);
    }

    /**
     * 获取需要审核的活动参与人信息
     *
     * @param userQuery
     * @return
     */
    @RequestMapping(value = "/activities/participants/pending", method = RequestMethod.GET)
    @Operation(summary = "获取需要审核的活动参与人信息")
    public RestResult<PageResult<ActivityParticipantDO>> userPendingParticipants(@Validated(QueryGroup.class) UserQuery userQuery) {
        PageResult<ActivityParticipantDO> result = userService.userPendingParticipants(userQuery);
        return new RestResult<>(ResultCodeConstant.CODE_000000, ResultCodeConstant.CODE_000000_MSG, result);
    }

    /**
     * position
     *
     * @param userPositionQuery 用户位置查询参数
     * @return
     */
    @RequestMapping(value = "/position", method = RequestMethod.GET)
    @Operation(summary = "position")
    public RestResult<UserDO> getUserPosition(@Validated(QueryGroup.class) UserPositionQuery userPositionQuery) {
        UserDO result = userService.getUserPosition(userPositionQuery);
        return new RestResult<>(ResultCodeConstant.CODE_000000, ResultCodeConstant.CODE_000000_MSG, result);
    }

    /**
     * surrounding-facilities
     *
     * @param surroundingFacilitiesQuery 周边运动场所查询参数
     * @return
     */
    @RequestMapping(value = "/surrounding-facilities", method = RequestMethod.GET)
    @Operation(summary = "surrounding-facilities")
    public RestResult<List<SportsFacilityDO>> getSurroundingFacilities(@Validated(QueryGroup.class) SurroundingFacilitiesQuery surroundingFacilitiesQuery) {
        List<SportsFacilityDO> result = userService.getSurroundingFacilities(surroundingFacilitiesQuery);
        return new RestResult<>(ResultCodeConstant.CODE_000000, ResultCodeConstant.CODE_000000_MSG, result);
    }

    /**
     * save-position
     *
     * @param userLocationDTO 用户位置信息保存参数
     * @return
     */
    @RequestMapping(value = "/save-position", method = RequestMethod.POST)
    @Operation(summary = "save-position")
    @ResponseBody
    public RestResult<Boolean> saveUserPosition(@RequestBody @Validated(CreateGroup.class) UserLocationDTO userLocationDTO) {
        Boolean result = userService.saveUserPosition(userLocationDTO);
        return new RestResult<>(ResultCodeConstant.CODE_000000, ResultCodeConstant.CODE_000000_MSG, result);
    }

    /**
     * facility-detail
     *
     * @param facilityDetailQuery 运动场所详情查询参数
     * @return
     */
    @RequestMapping(value = "/facility-detail", method = RequestMethod.GET)
    @Operation(summary = "facility-detail")
    public RestResult<SportsFacilityDO> getSportsFacilityDetail(@Validated(QueryGroup.class) FacilityDetailQuery facilityDetailQuery) {
        SportsFacilityDO result = userService.getSportsFacilityDetail(facilityDetailQuery);
        return new RestResult<>(ResultCodeConstant.CODE_000000, ResultCodeConstant.CODE_000000_MSG, result);
    }

    /**
     * 微信登录:根据wechatOpenid查找用户信息，如果用户不存在，则创建新用户，如果用户存在，返回用户信息
     *
     * @param userLoginDTO 登录DTO:用于微信登录和手机号登录的入参封装
     * @return
     */
    @RequestMapping(value = "/wechatLogin", method = RequestMethod.POST)
    @Operation(summary = "微信登录:根据wechatOpenid查找用户信息，如果用户不存在，则创建新用户，如果用户存在，返回用户信息")
    @ResponseBody
    public RestResult<UserDO> wechatLogin(@RequestBody @Validated(CreateGroup.class) UserLoginDTO userLoginDTO) {
        UserDO result = userService.wechatLogin(userLoginDTO);
        return new RestResult<>(ResultCodeConstant.CODE_000000, ResultCodeConstant.CODE_000000_MSG, result);
    }

    /**
     * 手机号登录:根据phoneNumber查找用户信息，如果用户不存在，则创建新用户，如果用户存在，返回用户信息
     *
     * @param userLoginDTO 登录DTO:用于微信登录和手机号登录的入参封装
     * @return
     */
    @RequestMapping(value = "/phoneLogin", method = RequestMethod.POST)
    @Operation(summary = "手机号登录:根据phoneNumber查找用户信息，如果用户不存在，则创建新用户，如果用户存在，返回用户信息")
    @ResponseBody
    public RestResult<UserDO> phoneLogin(@RequestBody @Validated(CreateGroup.class) UserLoginDTO userLoginDTO) {
        UserDO result = userService.phoneLogin(userLoginDTO);
        return new RestResult<>(ResultCodeConstant.CODE_000000, ResultCodeConstant.CODE_000000_MSG, result);
    }

    /**
     * 获取用户信息列表:根据查询条件获取用户信息列表，并进行分页
     *
     * @param userQuery
     * @return
     */
    @RequestMapping(value = "/infos", method = RequestMethod.GET)
    @Operation(summary = "获取用户信息列表:根据查询条件获取用户信息列表，并进行分页")
    public RestResult<PageResult<UserDO>> usersInfo(@Validated(QueryGroup.class) UserQuery userQuery) {
        PageResult<UserDO> result = userService.usersInfo(userQuery);
        return new RestResult<>(ResultCodeConstant.CODE_000000, ResultCodeConstant.CODE_000000_MSG, result);
    }

    /**
     * 获取用户信息:根据用户ID获取用户信息
     *
     * @param userQuery
     * @return
     */
    @RequestMapping(value = "/info1", method = RequestMethod.GET)
    @Operation(summary = "获取用户信息:根据用户ID获取用户信息")
    public RestResult<UserDO> userInfo1(@Validated(QueryGroup.class) UserQuery userQuery) {
        UserDO result = userService.userInfo1(userQuery);
        return new RestResult<>(ResultCodeConstant.CODE_000000, ResultCodeConstant.CODE_000000_MSG, result);
    }

    /**
     * 添加用户:添加新用户
     *
     * @param userDTO 用户DTO:用于用户操作的入参封装
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @Operation(summary = "添加用户:添加新用户")
    @ResponseBody
    public RestResult<Boolean> addUser(@RequestBody @Validated(CreateGroup.class) UserDTO userDTO) {
        Boolean result = userService.addUser(userDTO);
        return new RestResult<>(ResultCodeConstant.CODE_000000, ResultCodeConstant.CODE_000000_MSG, result);
    }

    /**
     * 更新用户:更新用户信息
     *
     * @param userDTO 用户DTO:用于用户操作的入参封装
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @Operation(summary = "更新用户:更新用户信息")
    @ResponseBody
    public RestResult<Boolean> updateUser(@RequestBody @Validated(UpdateGroup.class) UserDTO userDTO) {
        Boolean result = userService.updateUser(userDTO);
        return new RestResult<>(ResultCodeConstant.CODE_000000, ResultCodeConstant.CODE_000000_MSG, result);
    }

    /**
     * 删除用户:删除用户信息
     *
     * @param userDTO 用户DTO:用于用户操作的入参封装
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @Operation(summary = "删除用户:删除用户信息")
    @ResponseBody
    public RestResult<Boolean> deleteUser(@RequestBody @Validated(DeleteGroup.class) UserDTO userDTO) {
        Boolean result = userService.deleteUser(userDTO);
        return new RestResult<>(ResultCodeConstant.CODE_000000, ResultCodeConstant.CODE_000000_MSG, result);
    }
}
