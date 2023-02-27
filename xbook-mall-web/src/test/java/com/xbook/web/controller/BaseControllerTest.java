package com.xbook.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.xbook.common.constant.SysConstant;
import com.xbook.common.enums.CodeMsgEnum;
import com.xbook.common.redis.key.UserKey;
import com.xbook.common.utils.CookieUtil;
import com.xbook.entity.user.User;
import com.xbook.redis.service.RedisService;
import com.xbook.user.service.exception.UserException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.servlet.http.HttpServletRequest;

@WebMvcTest(BaseController.class)
public class BaseControllerTest {

    @MockBean
    private RedisService redisService;

    Integer getCurrentUserId(HttpServletRequest httpServletRequest) {
        String loginToken = CookieUtil.getCookieValue(httpServletRequest, SysConstant.LOGIN_TOKEN);
        if (StringUtils.isBlank(loginToken)) {
            throw new UserException(CodeMsgEnum.SESSION_ERROR);
        }
        String jsonStr = redisService.get(UserKey.loginUser, loginToken);
        if (StringUtils.isBlank(jsonStr)) {
            throw new UserException(CodeMsgEnum.SESSION_ERROR);
        }
        User user = JSON.parseObject(jsonStr, User.class);
        return user.getId();
    }

}
