package cn.helloallen.domain.service.impl;

import cn.helloallen.domain.constant.ResultCodeConstant;
import cn.helloallen.domain.dao.*;
import cn.helloallen.domain.exception.BusinessException;
import cn.helloallen.domain.pojo.domain.ActivityDO;
import cn.helloallen.domain.pojo.domain.ActivityParticipantDO;
import cn.helloallen.domain.pojo.domain.SportsFacilityDO;
import cn.helloallen.domain.pojo.domain.UserActivityLikeDO;
import cn.helloallen.domain.pojo.dto.ActivityCommentDTO;
import cn.helloallen.domain.pojo.dto.ActivityDTO;
import cn.helloallen.domain.pojo.dto.ActivityLikeDTO;
import cn.helloallen.domain.pojo.dto.ActivityParticipantDTO;
import cn.helloallen.domain.pojo.query.ActivityQuery;
import cn.helloallen.domain.pojo.query.LocationQuery;
import cn.helloallen.domain.pojo.vo.PageResult;
import cn.helloallen.domain.service.ActivityService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityParticipantsMapper activityParticipantsMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SportsFacilityMapper sportsFacilityMapper;

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private UserActivityLikesMapper userActivityLikesMapper;

    @Override
    public ActivityDO activityInfo(ActivityQuery activityQuery) {
        QueryWrapper<ActivityDO> queryWrapper = Wrappers.query();
        queryWrapper.eq("activity_id", activityQuery.getActivityId());
        ActivityDO activityDO = activityMapper.selectOne(queryWrapper);
        if (activityDO == null) {
            throw new BusinessException(ResultCodeConstant.CODE_000001, ResultCodeConstant.CODE_000001_MSG);
        }
        return activityDO;
    }

    @Override
    public Boolean likeActivity(ActivityLikeDTO activityLikeDTO) {
        QueryWrapper<UserActivityLikeDO> queryWrapper = Wrappers.query();
        queryWrapper.eq("user_id", activityLikeDTO.getUserId()).eq("activity_id", activityLikeDTO.getActivityId());
        UserActivityLikeDO existingLike = userActivityLikesMapper.selectOne(queryWrapper);
        if (existingLike != null) {
            throw new BusinessException(ResultCodeConstant.CODE_000001, ResultCodeConstant.CODE_000001_MSG);
        }
        UserActivityLikeDO likeDO = new UserActivityLikeDO();
        likeDO.setUserId(activityLikeDTO.getUserId());
        likeDO.setActivityId(activityLikeDTO.getActivityId());
        likeDO.setCreateBy(activityLikeDTO.getUserId());
        likeDO.setCreateTime(new Date());
        int result = userActivityLikesMapper.insert(likeDO);
        return result > 0;
    }

    @Override
    public Boolean commentActivity(ActivityCommentDTO activityCommentDTO) {
        ActivityDO activityDO = activityMapper.selectById(activityCommentDTO.getActivityId());
        if (activityDO == null) {
            throw new BusinessException(ResultCodeConstant.CODE_000001, ResultCodeConstant.CODE_000001_MSG);
        }
        int result = activityMapper.insertComment(activityCommentDTO.toActivityCommentDO());
        return result > 0;
    }

    @Override
    public Boolean applyActivity(ActivityParticipantDTO activityParticipantDTO) {
        ActivityDO activityDO = activityMapper.selectById(activityParticipantDTO.getActivityId());
        if (activityDO == null) {
            throw new BusinessException(ResultCodeConstant.CODE_000001, ResultCodeConstant.CODE_000001_MSG);
        }
        QueryWrapper<ActivityParticipantDO> participantWrapper = Wrappers.query();
        participantWrapper.eq("user_id", activityParticipantDTO.getUserId()).eq("activity_id", activityParticipantDTO.getActivityId());
        ActivityParticipantDO existingParticipant = activityParticipantsMapper.selectOne(participantWrapper);
        if (existingParticipant != null) {
            throw new BusinessException(ResultCodeConstant.CODE_000001, ResultCodeConstant.CODE_000001_MSG);
        }
        long participantCount = activityParticipantsMapper.selectCountByActivityId(activityParticipantDTO.getActivityId());
        if (activityDO.getMaxParticipants() != null && participantCount >= activityDO.getMaxParticipants()) {
            throw new BusinessException(ResultCodeConstant.CODE_000001, ResultCodeConstant.CODE_000001_MSG);
        }
        ActivityParticipantDO participantDO = new ActivityParticipantDO();
        participantDO.setUserId(activityParticipantDTO.getUserId());
        participantDO.setActivityId(activityParticipantDTO.getActivityId());
        participantDO.setStatus("pending");
        participantDO.setCreateBy(activityParticipantDTO.getUserId());
        participantDO.setCreateTime(new Date());
        int result = activityParticipantsMapper.insert(participantDO);
        return result > 0;
    }

    @Override
    public PageResult<SportsFacilityDO> findNearbyFacilities(LocationQuery locationQuery) {
        // 调用高德地图API获取用户位置周边的运动场所信息
//        List<SportsFacilityDO> facilities = callGaodeMapAPI(locationQuery.getLatitude(), locationQuery.getLongitude());
//        if (facilities == null) {
//            throw new BusinessException(ResultCodeConstant.CODE_000001, ResultCodeConstant.CODE_000001_MSG);
//        }
//        return new PageResult<>(facilities);
        return null;
    }

    @Override
    public Boolean addActivity(ActivityDTO activityDTO) {
        // 验证用户信息是否存在
        if (userMapper.selectById(activityDTO.getUserId()) == null) {
            throw new BusinessException(ResultCodeConstant.CODE_000001, ResultCodeConstant.CODE_000001_MSG);
        }
        // 验证输入参数的有效性
        if (!validateActivityInput(activityDTO)) {
            throw new BusinessException(ResultCodeConstant.CODE_000001, ResultCodeConstant.CODE_000001_MSG);
        }
        // 将活动信息保存到数据库
        SportsFacilityDO facility = sportsFacilityMapper.selectById(activityDTO.getUserId());
        if (facility == null) {
            throw new BusinessException(ResultCodeConstant.CODE_000001, ResultCodeConstant.CODE_000001_MSG);
        }
        ActivityDO activityDO = new ActivityDO();
        activityDO.setUserId(activityDTO.getUserId());
        activityDO.setTitle(activityDTO.getTitle());
        activityDO.setContent(activityDTO.getContent());
        activityDO.setLocation(activityDTO.getLocation());
        activityDO.setType(activityDTO.getType());
        activityDO.setStartTime(activityDTO.getStartTime());
        activityDO.setMaxParticipants(activityDTO.getMaxParticipants());
        activityDO.setAverageConsumption(activityDTO.getAverageConsumption());
        activityDO.setContactInfo(activityDTO.getContactInfo());
        activityDO.setMainImageUrl(activityDTO.getMainImageUrl());
        activityDO.setDetailImageUrls(activityDTO.getDetailImageUrls());
        activityDO.setCreateBy(activityDTO.getUserId());
        activityDO.setCreateTime(new Date());
        activityDO.setUpdateBy(activityDTO.getUserId());
        activityDO.setUpdateTime(new Date());
        int result = activityMapper.insert(activityDO);
        return result > 0;
    }

    private List<SportsFacilityDO> callGaodeMapAPI(Double latitude, Double longitude) {
        // 模拟调用高德地图API，实际需要根据API文档编写调用逻辑
        return new ArrayList<>();
    }

    private Boolean validateActivityInput(ActivityDTO activityDTO) {
        // 模拟验证输入参数的有效性，实际需要根据业务规则编写验证逻辑
        return true;
    }
}

