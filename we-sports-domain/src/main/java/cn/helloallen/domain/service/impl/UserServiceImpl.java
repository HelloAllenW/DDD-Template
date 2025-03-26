package cn.helloallen.domain.service.impl;

import cn.helloallen.domain.constant.ResultCodeConstant;
import cn.helloallen.domain.dao.ActivityMapper;
import cn.helloallen.domain.dao.ActivityParticipantMapper;
import cn.helloallen.domain.dao.SportsFacilityMapper;
import cn.helloallen.domain.dao.UserMapper;
import cn.helloallen.domain.exception.BusinessException;
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
import cn.helloallen.domain.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SportsFacilityMapper sportsFacilityMapper;

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private ActivityParticipantMapper activityParticipantMapper;

    @Override
    public UserDO userInfo(UserQuery userQuery) {
        UserDO userDO = userMapper.selectById(userQuery.getUserId());
        if (userDO == null) {
            throw new BusinessException(ResultCodeConstant.CODE_000001, ResultCodeConstant.CODE_000001_MSG);
        }
        return userDO;
    }

    @Override
    public PageResult<ActivityDO> userInitiatedActivities(UserQuery userQuery) {
        QueryWrapper<ActivityDO> queryWrapper = Wrappers.query(new ActivityDO()).eq("user_id", userQuery.getUserId());
        IPage<ActivityDO> page = new Page<>(userQuery.getPageIndex(), userQuery.getPageSize());
        IPage<ActivityDO> activityPage = activityMapper.selectPage(page, queryWrapper);
        if (activityPage.getRecords().isEmpty()) {
            throw new BusinessException(ResultCodeConstant.CODE_000001, ResultCodeConstant.CODE_000001_MSG);
        }
//        return new PageResult<>(activityPage);
        return null;
    }

    @Override
    public PageResult<ActivityDO> userParticipatedActivities(UserQuery userQuery) {
        QueryWrapper<ActivityParticipantDO> participantWrapper = Wrappers.query(new ActivityParticipantDO()).eq("user_id", userQuery.getUserId());
        List<ActivityParticipantDO> participants = activityParticipantMapper.selectList(participantWrapper);
        if (participants.isEmpty()) {
            throw new BusinessException(ResultCodeConstant.CODE_000001, ResultCodeConstant.CODE_000001_MSG);
        }
        List<Integer> activityIds = new ArrayList<>();
        for (ActivityParticipantDO participant : participants) {
            activityIds.add(participant.getActivityId());
        }
        QueryWrapper<ActivityDO> activityWrapper = Wrappers.query(new ActivityDO()).in("activity_id", activityIds);
        IPage<ActivityDO> page = new Page<>(userQuery.getPageIndex(), userQuery.getPageSize());
        IPage<ActivityDO> activityPage = activityMapper.selectPage(page, activityWrapper);
//        return new PageResult<>(activityPage);
        return null;
    }

    @Override
    public PageResult<ActivityParticipantDO> userPendingParticipants(UserQuery userQuery) {
        QueryWrapper<ActivityDO> activityWrapper = Wrappers.query(new ActivityDO()).eq("user_id", userQuery.getUserId());
        List<ActivityDO> activities = activityMapper.selectList(activityWrapper);
        if (activities.isEmpty()) {
            throw new BusinessException(ResultCodeConstant.CODE_000001, ResultCodeConstant.CODE_000001_MSG);
        }
        List<Integer> activityIds = new ArrayList<>();
        for (ActivityDO activity : activities) {
            activityIds.add(activity.getActivityId());
        }
        QueryWrapper<ActivityParticipantDO> participantWrapper = Wrappers.query(new ActivityParticipantDO()).in("activity_id", activityIds).eq("status", "pending");
        IPage<ActivityParticipantDO> page = new Page<>(userQuery.getPageIndex(), userQuery.getPageSize());
        IPage<ActivityParticipantDO> participantPage = activityParticipantMapper.selectPage(page, participantWrapper);
        if (participantPage.getRecords().isEmpty()) {
            throw new BusinessException(ResultCodeConstant.CODE_000001, ResultCodeConstant.CODE_000001_MSG);
        }
//        return new PageResult<>(participantPage);
        return null;
    }

    @Override
    public UserDO getUserPosition(UserPositionQuery userPositionQuery) {
        // 通过userId获取用户位置信息
        UserDO userDO = userMapper.selectById(userPositionQuery.getUserId());
        return userDO;
    }

    @Override
    public List<SportsFacilityDO> getSurroundingFacilities(SurroundingFacilitiesQuery surroundingFacilitiesQuery) {
        // 调用高德地图API获取周边运动场所信息
        // 这里假设我们有一个方法调用高德API并返回SportsFacilityDO列表
        List<SportsFacilityDO> facilities = fetchSurroundingFacilities(surroundingFacilitiesQuery.getLatitude(), surroundingFacilitiesQuery.getLongitude(), surroundingFacilitiesQuery.getRadius());
        return facilities;
    }

    @Override
    public Boolean saveUserPosition(UserLocationDTO userLocationDTO) {
        // 将用户位置信息保存到数据库
        UserDO userDO = new UserDO();
        userDO.setId(userLocationDTO.getUserId());
        userDO.setWechatOpenid(userLocationDTO.getWechatOpenid());
        userDO.setPhoneNumber(userLocationDTO.getPhoneNumber());
        // 假设这里有一个方法可以更新用户的地理位置信息
        userDO.setLocation(userLocationDTO.getLatitude() + "," + userLocationDTO.getLongitude());
        int result = userMapper.updateById(userDO);
        return result > 0;
    }

    @Override
    public SportsFacilityDO getSportsFacilityDetail(FacilityDetailQuery facilityDetailQuery) {
        // 通过facilityId获取运动场所详情信息
        SportsFacilityDO facilityDO = sportsFacilityMapper.selectById(facilityDetailQuery.getFacilityId());
        return facilityDO;
    }

    @Override
    public UserDO wechatLogin(UserLoginDTO userLoginDTO) {
        QueryWrapper<UserDO> queryWrapper = Wrappers.query(new UserDO()).eq("wechat_openid", userLoginDTO.getWechatOpenid());
        UserDO userDO = userMapper.selectOne(queryWrapper);
        if (userDO == null) {
            userDO = new UserDO();
            userDO.setWechatOpenid(userLoginDTO.getWechatOpenid());
            userDO.setCreateTime(new Date());
            userMapper.insert(userDO);
            return userDO;
        } else {
            return userDO;
        }
    }

    @Override
    public UserDO phoneLogin(UserLoginDTO userLoginDTO) {
        QueryWrapper<UserDO> queryWrapper = Wrappers.query(new UserDO()).eq("phone_number", userLoginDTO.getPhoneNumber());
        UserDO userDO = userMapper.selectOne(queryWrapper);
        if (userDO == null) {
            userDO = new UserDO();
            userDO.setPhoneNumber(userLoginDTO.getPhoneNumber());
            userDO.setCreateTime(new Date());
            userMapper.insert(userDO);
            return userDO;
        } else {
            return userDO;
        }
    }

    @Override
    public PageResult<UserDO> usersInfo(UserQuery userQuery) {
        Page<UserDO> page = new Page<>(userQuery.getPageIndex(), userQuery.getPageSize());
        IPage<UserDO> userPage = userMapper.selectPage(page, Wrappers.emptyWrapper());
//        PageResult<UserDO> pageResult = new PageResult<>(userPage.getTotal(), userPage.getRecords());
//        return pageResult;
        return null;
    }

    @Override
    public UserDO userInfo1(UserQuery userQuery) {
        UserDO userDO = userMapper.selectById(userQuery.getUserId());
        return userDO;
    }

    @Override
    public Boolean addUser(UserDTO userDTO) {
        UserDO userDO = new UserDO();
        userDO.setUsername(userDTO.getUsername());
        userDO.setWechatOpenid(userDTO.getWechatOpenid());
        userDO.setPhoneNumber(userDTO.getPhoneNumber());
        userDO.setCreateTime(new Date());
        int result = userMapper.insert(userDO);
        return result > 0;
    }

    @Override
    public Boolean updateUser(UserDTO userDTO) {
        UserDO userDO = new UserDO();
        userDO.setId(userDTO.getUserId());
        userDO.setUsername(userDTO.getUsername());
        userDO.setWechatOpenid(userDTO.getWechatOpenid());
        userDO.setPhoneNumber(userDTO.getPhoneNumber());
        userDO.setUpdateTime(new Date());
        int result = userMapper.updateById(userDO);
        return result > 0;
    }

    @Override
    public Boolean deleteUser(UserDTO userDTO) {
        int result = userMapper.deleteById(userDTO.getUserId());
        return result > 0;
    }

    private List<SportsFacilityDO> fetchSurroundingFacilities(Double latitude, Double longitude, Integer radius) {
        // 这里应该调用高德地图API并解析返回结果
        // 为了简化示例，我们直接返回一个空列表
        return null;
    }
}
