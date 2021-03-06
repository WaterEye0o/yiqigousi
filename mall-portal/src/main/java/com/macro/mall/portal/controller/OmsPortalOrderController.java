package com.macro.mall.portal.controller;

import com.macro.mall.portal.domain.CommonResult;
import com.macro.mall.portal.domain.ConfirmOrderResult;
import com.macro.mall.portal.domain.OrderParam;
import com.macro.mall.portal.service.OmsPortalOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单管理Controller
 * Created by macro on 2018/8/30.
 */
@Controller
@Api(tags = "OmsPortalOrderController",description = "订单管理")
@RequestMapping("/order")
public class OmsPortalOrderController {
    @Autowired
    private OmsPortalOrderService portalOrderService;
    @ApiOperation("根据购物车信息生成确认单信息")
    @RequestMapping(value = "/generateConfirmOrder",method = RequestMethod.POST)
    @ResponseBody
    public Object generateConfirmOrder(){
        ConfirmOrderResult confirmOrderResult = portalOrderService.generateConfirmOrder();
        return new CommonResult().success(confirmOrderResult);
    }

    @ApiOperation("微信小程序根据购物车信息生成确认单信息")
    @RequestMapping(value = "/generateConfirmOrderForWXAPP",method = RequestMethod.POST)
    @ResponseBody
    public Object generateConfirmOrderForWXAPP(@RequestParam String openId,
                                               @RequestParam List<Long> goodsIds){
        ConfirmOrderResult confirmOrderResult = portalOrderService.generateConfirmOrderForWXAPP(openId,goodsIds);
        return new CommonResult().success(confirmOrderResult);
    }

//    @ApiOperation("根据购物车信息生成订单")
//    @RequestMapping(value = "/generateOrder",method = RequestMethod.POST)
//    @ResponseBody
//    public Object generateOrder(@RequestBody OrderParam orderParam){
//        return portalOrderService.generateOrder(orderParam);
//    }

    @ApiOperation("根据购物车信息生成订单")
    @RequestMapping(value = "/generateOrderForWXAPP",method = RequestMethod.POST)
    @ResponseBody
    public Object generateOrderForWXAPP(OrderParam orderParam,@RequestParam String openId,@RequestParam List<Long> goodsIds){
        return portalOrderService.generateOrderForWXAPP(orderParam,openId,goodsIds);
    }
    @ApiOperation("微信小程序获取全部订单")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public Object list(@RequestParam String openId,
                       @RequestParam(value = "status", required = false) String status){
        return new CommonResult().success(portalOrderService.list(openId,status));
    }

    @ApiOperation("支付成功的回调")
    @RequestMapping(value = "/paySuccess",method = RequestMethod.POST)
    @ResponseBody
    public Object paySuccess(@RequestParam Long orderId){
        return portalOrderService.paySuccess(orderId);
    }

    @ApiOperation("自动取消超时订单")
    @RequestMapping(value = "/cancelTimeOutOrder",method = RequestMethod.POST)
    @ResponseBody
    public Object cancelTimeOutOrder(){
        return portalOrderService.cancelTimeOutOrder();
    }

    @ApiOperation("取消单个超时订单")
    @RequestMapping(value = "/cancelOrder",method = RequestMethod.POST)
    @ResponseBody
    public Object cancelOrder(Long orderId){
        portalOrderService.sendDelayMessageCancelOrder(orderId);
        return new CommonResult().success(null);
    }
}
