package cn.helloallen.domain.service;

import cn.helloallen.domain.pojo.domain.ActivityDO;
import cn.helloallen.domain.pojo.domain.ActivityParticipantDO;
import cn.helloallen.domain.pojo.domain.SportsFacilityDO;
import cn.helloallen.domain.pojo.domain.UserDO;
import cn.helloallen.domain.pojo.dto.UserDTO;
import cn.helloallen.domain.pojo.dto.UserLocationDTO;
import cn.helloallen.domain.pojo.dto.UserLoginDTO;
import cn.helloallen.domain.pojo.query.FacilityDetailQuery;
import cn.helloallen.domain.pojo.query.SurroundingFacilitiesQuery;
import cn.helloallen.domain.pojo.query.UserPositionQuery;
import cn.helloallen.domain.pojo.query.UserQuery;
import cn.helloallen.domain.pojo.vo.PageResult;

import java.util.List;

public interface UserService {
    /**
     * 获取用户登录信息
     *
     * @param userQuery
     * @return  用户实体
     */
    UserDO userInfo(UserQuery userQuery);

    /**
     * 获取用户发起的活动列表
     *
     * @param userQuery
     * @return
     */
    PageResult<ActivityDO> userInitiatedActivities(UserQuery userQuery);

    /**
     * 获取用户参与的活动列表
     *
     * @param userQuery
     * @return
     */
    PageResult<ActivityDO> userParticipatedActivities(UserQuery userQuery);

    /**
     * 获取需要审核的活动参与人信息
     *
     * @param userQuery
     * @return
     */
    PageResult<ActivityParticipantDO> userPendingParticipants(UserQuery userQuery);

    /**
     * position
     *
     * @param userPositionQuery 用户位置查询参数
     * @return  用户实体
     */
    UserDO getUserPosition(UserPositionQuery userPositionQuery);

    /**
     * surrounding-facilities
     *
     * @param surroundingFacilitiesQuery 周边运动场所查询参数
     * @return
     */
    List<SportsFacilityDO> getSurroundingFacilities(SurroundingFacilitiesQuery surroundingFacilitiesQuery);

    /**
     * save-position
     *
     * @param userLocationDTO 用户位置信息保存参数
     * @return
     */
    Boolean saveUserPosition(UserLocationDTO userLocationDTO);

    /**
     * facility-detail
     *
     * @param facilityDetailQuery 运动场所详情查询参数
     * @return  运动场所信息实体对象
     */
    SportsFacilityDO getSportsFacilityDetail(FacilityDetailQuery facilityDetailQuery);

    /**
     * 微信登录:根据wechatOpenid查找用户信息，如果用户不存在，则创建新用户，如果用户存在，返回用户信息
     *
     * @param userLoginDTO 登录DTO:用于微信登录和手机号登录的入参封装
     * @return  用户实体
     */
    UserDO wechatLogin(UserLoginDTO userLoginDTO);

    /**
     * 手机号登录:根据phoneNumber查找用户信息，如果用户不存在，则创建新用户，如果用户存在，返回用户信息
     *
     * @param userLoginDTO 登录DTO:用于微信登录和手机号登录的入参封装
     * @return  用户实体
     */
    UserDO phoneLogin(UserLoginDTO userLoginDTO);

    /**
     * 获取用户信息列表:根据查询条件获取用户信息列表，并进行分页
     *
     * @param userQuery
     * @return
     */
    PageResult<UserDO> usersInfo(UserQuery userQuery);

    /**
     * 获取用户信息:根据用户ID获取用户信息
     *
     * @param userQuery
     * @return  用户实体
     */
    UserDO userInfo1(UserQuery userQuery);

    /**
     * 添加用户:添加新用户
     *
     * @param userDTO 用户DTO:用于用户操作的入参封装
     * @return
     */
    Boolean addUser(UserDTO userDTO);

    /**
     * 更新用户:更新用户信息
     *
     * @param userDTO 用户DTO:用于用户操作的入参封装
     * @return
     */
    Boolean updateUser(UserDTO userDTO);

    /**
     * 删除用户:删除用户信息
     *
     * @param userDTO 用户DTO:用于用户操作的入参封装
     * @return
     */
    Boolean deleteUser(UserDTO userDTO);
}
