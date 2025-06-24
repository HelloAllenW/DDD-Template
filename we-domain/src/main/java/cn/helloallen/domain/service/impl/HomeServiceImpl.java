package cn.helloallen.domain.service.impl;

import cn.helloallen.domain.constant.ResultCodeConstant;
import cn.helloallen.domain.dao.ActivityMapper;
import cn.helloallen.domain.dao.UserMapper;
import cn.helloallen.domain.exception.BusinessException;
import cn.helloallen.domain.pojo.domain.ActivityDO;
import cn.helloallen.domain.pojo.domain.UserDO;
import cn.helloallen.domain.pojo.query.ActivityQuery;
import cn.helloallen.domain.pojo.query.UserLocationQuery;
import cn.helloallen.domain.pojo.query.UserQuery;
import cn.helloallen.domain.pojo.vo.ActivityVO;
import cn.helloallen.domain.pojo.vo.LocationVO;
import cn.helloallen.domain.pojo.vo.PageResult;
import cn.helloallen.domain.service.HomeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public LocationVO getUserLocation(UserQuery userQuery) {
//        try {
//            LocationVO location = fetchUserLocationFromAMap(userQuery.getUserId());
//            return location;
//        } catch (Exception e) {
//            throw new BusinessException(ResultCodeConstant.CODE_000001, ResultCodeConstant.CODE_000001_MSG);
//        }
        return null;
    }

    @Override
    public List<ActivityVO> getNearbyFacilities(UserLocationQuery userLocationQuery) {
//        try {
//            List<ActivityVO> facilities = fetchNearbyFacilitiesFromAMap(userLocationQuery.getUserId(), userLocationQuery.getLocation());
//            return facilities;
//        } catch (Exception e) {
//            throw new BusinessException(ResultCodeConstant.CODE_000001, ResultCodeConstant.CODE_000001_MSG);
//        }
        return null;
    }

    @Override
    public PageResult<ActivityVO> searchActivities(ActivityQuery activityQuery) {
        try {
            IPage<ActivityDO> page = new Page<>(activityQuery.getPageIndex(), activityQuery.getPageSize());
            QueryWrapper<ActivityDO> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("location", activityQuery.getLocation()).lt("start_time", new Date()).orderByDesc("start_time");
            IPage<ActivityDO> activityPage = activityMapper.selectPage(page, queryWrapper);
            PageResult<ActivityVO> pageResult = convertToPageResult(activityPage);
            return pageResult;
        } catch (Exception e) {
            throw new BusinessException(ResultCodeConstant.CODE_999999, ResultCodeConstant.CODE_999999_MSG);
        }
    }

    @Override
    public ActivityVO getActivityDetails(ActivityQuery activityQuery) {
        try {
            ActivityDO activityDO = activityMapper.selectById(activityQuery.getActivityId());
            ActivityVO activityVO = convertToActivityVO(activityDO);
            return activityVO;
        } catch (Exception e) {
            throw new BusinessException(ResultCodeConstant.CODE_999999, ResultCodeConstant.CODE_999999_MSG);
        }
    }

    @Override
    public UserDO getActivityOrganizer(ActivityQuery activityQuery) {
        try {
            ActivityDO activityDO = activityMapper.selectById(activityQuery.getActivityId());
            UserDO userDO = userMapper.selectById(activityDO.getUserId());
            return userDO;
        } catch (Exception e) {
            throw new BusinessException(ResultCodeConstant.CODE_999999, ResultCodeConstant.CODE_999999_MSG);
        }
    }

    @Override
    public String[] getActivityImages(ActivityQuery activityQuery) {
        try {
            ActivityDO activityDO = activityMapper.selectById(activityQuery.getActivityId());
            String[] images = parseImageUrls(activityDO.getDetailImageUrls());
            return images;
        } catch (Exception e) {
            throw new BusinessException(ResultCodeConstant.CODE_999999, ResultCodeConstant.CODE_999999_MSG);
        }
    }

    private PageResult<ActivityVO> convertToPageResult(IPage<ActivityDO> activityPage) {
        List<ActivityVO> activityVOs = new ArrayList<>();
        for (ActivityDO activityDO : activityPage.getRecords()) {
            activityVOs.add(convertToActivityVO(activityDO));
        }
        return new PageResult<>(activityPage.getTotal(), activityPage.getSize(), activityPage.getCurrent(), activityVOs);
    }

    private ActivityVO convertToActivityVO(ActivityDO activityDO) {
        ActivityVO activityVO = new ActivityVO();
        activityVO.setActivityId(activityDO.getActivityId());
        activityVO.setTitle(activityDO.getTitle());
        activityVO.setContent(activityDO.getContent());
        activityVO.setLocation(activityDO.getLocation());
        activityVO.setType(activityDO.getType());
        activityVO.setStartTime(activityDO.getStartTime());
        activityVO.setMaxParticipants(activityDO.getMaxParticipants());
        activityVO.setAverageConsumption(activityDO.getAverageConsumption());
        activityVO.setContactInfo(activityDO.getContactInfo());
        activityVO.setMainImageUrl(activityDO.getMainImageUrl());
        activityVO.setDetailImageUrls(parseImageUrls(activityDO.getDetailImageUrls()));
        return activityVO;
    }

    private String[] parseImageUrls(String detailImageUrls) {
        return detailImageUrls != null ? detailImageUrls.split(",") : new String[0];
    }
}
