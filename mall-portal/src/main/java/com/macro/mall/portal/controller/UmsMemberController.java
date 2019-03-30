package com.macro.mall.portal.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.macro.mall.portal.config.WxMaConfiguration;
import com.macro.mall.portal.domain.CommonResult;
import com.macro.mall.portal.service.UmsMemberService;
import com.macro.mall.portal.util.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 会员登录注册管理Controller
 * Created by macro on 2018/8/3.
 */
@Controller
@Api(tags = "UmsMemberController", description = "会员登录注册管理")
@RequestMapping("/sso")
public class UmsMemberController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UmsMemberService memberService;

    @ApiOperation("微信小程序获取用户信息")
    @RequestMapping(value = "/getUserInfoByWXAPPOpenId", method = RequestMethod.POST)
    @ResponseBody
    public Object getUserInfoByWXAPPOpenId(@RequestParam String openId) {
        return new CommonResult().success(memberService.getMemberByWXAPPOpenId(openId)) ;
    }

    @ApiOperation("微信小程序注册")
    @RequestMapping(value = "/registerForWXAPP", method = RequestMethod.POST)
    @ResponseBody
    public Object registerForWXAPP(@RequestParam String openId,
                                   @RequestParam String username,
                                   @RequestParam String password,
                                   @RequestParam String telephone,
                                   @RequestParam String authCode) {
        return memberService.registerForWXAPP(username, password, telephone, authCode,openId);
    }

    @ApiOperation("注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Object register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String telephone,
                           @RequestParam String authCode) {
        return memberService.register(username, password, telephone, authCode);
    }
    @ApiOperation("微信小程序注册")
    @RequestMapping(value = "/jscode2session", method = RequestMethod.POST)
    @ResponseBody
    public Object jscode2session(@RequestParam String jsCode)  {
        if (StringUtils.isBlank(jsCode)) {
            return "empty jscode";
        }

        final WxMaService wxService = WxMaConfiguration.getMaService("wx1c0f5a0255da0c55");

        try {
            WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(jsCode);
            this.logger.info(session.getSessionKey());
            this.logger.info(session.getOpenid());
            //TODO 可以增加自己的逻辑，关联业务相关数据
            return new CommonResult().success("获取openid成功",session);
        } catch (WxErrorException e) {
            this.logger.error(e.getMessage(), e);
            return e.toString();
        }
    }

    @ApiOperation("获取验证码")
    @RequestMapping(value = "/getAuthCode", method = RequestMethod.GET)
    @ResponseBody
    public Object getAuthCode(@RequestParam String telephone) {
        return memberService.generateAuthCode(telephone);
    }

    @ApiOperation("修改密码")
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public Object updatePassword(@RequestParam String telephone,
                                 @RequestParam String password,
                                 @RequestParam String authCode) {
        return memberService.updatePassword(telephone,password,authCode);
    }
}
