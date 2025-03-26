package cn.helloallen.domain.dao;

import cn.helloallen.domain.pojo.domain.UserDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends BaseMapper<UserDO> {

    // 根据用户ID查询用户信息:根据活动ID查询活动发起人信息
    @Select("SELECT user_id, username, wechat_openid, phone_number FROM users WHERE user_id = #{userId}")
    UserDO selectById(@Param("userId") Integer userId);

    /**
     * 根据微信OpenID查询用户信息
     *
     * @Param wechatOpenid 微信OpenID
     * @Return 用户信息
     */
    UserDO selectByWechatOpenid(@Param("wechatOpenid") String wechatOpenid);

    /**
     * 根据手机号码查询用户信息
     *
     * @Param phoneNumber 手机号码
     * @Return 用户信息
     */
    UserDO selectByPhoneNumber(@Param("phoneNumber") String phoneNumber);
}
