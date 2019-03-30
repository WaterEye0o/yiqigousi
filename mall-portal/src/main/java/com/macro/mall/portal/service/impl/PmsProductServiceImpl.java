package com.macro.mall.portal.service.impl;

import com.macro.mall.portal.dao.PortalProductDao;
import com.macro.mall.portal.dto.PmsProductResult;
import com.macro.mall.portal.service.PmsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PmsProductServiceImpl implements PmsProductService {
    @Autowired
    private PortalProductDao productDao;

    @Override
    public PmsProductResult getProductById(Long id) {
        return productDao.getProduct(id);
    }
}
