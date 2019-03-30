package com.macro.mall.portal.service;

import com.macro.mall.portal.dto.PmsProductResult;

public interface PmsProductService {
    /**
     * 根据商品编号获取商品信息
     */
    PmsProductResult getProductById(Long id);
}
