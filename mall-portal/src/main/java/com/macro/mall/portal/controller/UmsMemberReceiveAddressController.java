package com.macro.mall.portal.controller;

import com.macro.mall.model.UmsMemberReceiveAddress;
import com.macro.mall.portal.domain.CommonResult;
import com.macro.mall.portal.service.UmsMemberReceiveAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会员收货地址管理Controller
 * Created by macro on 2018/8/28.
 */
@Controller
@Api(tags = "UmsMemberReceiveAddressController", description = "会员收货地址管理")
@RequestMapping("/member/address")
public class UmsMemberReceiveAddressController {
    @Autowired
    private UmsMemberReceiveAddressService memberReceiveAddressService;
    @ApiOperation("添加收货地址")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(@RequestBody UmsMemberReceiveAddress address) {
        int count = memberReceiveAddressService.add(address);
        if(count>0){
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }

    @ApiOperation("微信小程序添加收货地址")
    @RequestMapping(value = "/addForWXAPP", method = RequestMethod.POST)
    @ResponseBody
    public Object addForWXAPP( UmsMemberReceiveAddress address,@RequestParam String openId) {
        int count = memberReceiveAddressService.addForWXAPP(address,openId);
        if(count>0){
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }

    @ApiOperation("删除收货地址")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object delete(@PathVariable Long id) {
        int count = memberReceiveAddressService.delete(id);
        if(count>0){
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }

    @ApiOperation("修改收货地址")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable Long id,@RequestBody UmsMemberReceiveAddress address) {
        int count = memberReceiveAddressService.update(id,address);
        if(count>0){
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }

    @ApiOperation("显示所有收货地址")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list() {
        List<UmsMemberReceiveAddress> addressList = memberReceiveAddressService.list();
        return new CommonResult().success(addressList);
    }

    @ApiOperation("微信小程序显示所有收货地址")
    @RequestMapping(value = "/listForWXAPP", method = RequestMethod.GET)
    @ResponseBody
    public Object listForWXAPP(@RequestParam String openId) {
        List<UmsMemberReceiveAddress> addressList = memberReceiveAddressService.listForWXAPP( openId );
        return new CommonResult().success(addressList);
    }

    @ApiOperation("显示具体收货地址")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object getItem(@PathVariable Long id) {
        UmsMemberReceiveAddress address = memberReceiveAddressService.getItem(id);
        return new CommonResult().success(address);
    }
}
