package com.macro.mall.dao;

import com.macro.mall.dto.OmsOrderDeliveryParam;
import com.macro.mall.dto.OmsOrderDetail;
import com.macro.mall.dto.OmsOrderQueryParam;
import com.macro.mall.model.OmsOrder;
import com.macro.mall.model.OmsOrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单自定义查询Dao
 * Created by macro on 2018/10/12.
 */
public interface OmsOrderDao {
    /**
     * 条件查询订单
     */
    List<OmsOrder> getList(@Param("queryParam") OmsOrderQueryParam queryParam);

    /**
     * 批量发货
     */
    int delivery(@Param("list") List<OmsOrderDeliveryParam> deliveryParamList);

    /**
     * 获取订单详情
     */
    OmsOrderDetail getDetail(@Param("id") Long id);

    /**
     * 修改 pms_sku_stock表的锁定库存及真实库存
     */
    int updateSkuStock(@Param("itemList") List<OmsOrderItem> orderItemList);
}
