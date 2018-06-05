package com.monochrome.tmall.service.impl;

import com.monochrome.tmall.mapper.ProductImageMapper;
import com.monochrome.tmall.pojo.ProductImage;
import com.monochrome.tmall.pojo.ProductImageExample;
import com.monochrome.tmall.service.ProductImageService;
import com.monochrome.tmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
public class ProductImageServiceImpl implements ProductImageService {

    @Autowired
    ProductImageMapper productImageMapper;

    @Autowired
    ProductService productService;

    @Override
    public void add(ProductImage productImage) {
        productImageMapper.insert(productImage);
    }

    @Override
    public void delete(int id) {
        productImageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(ProductImage productImage) {
        productImageMapper.updateByPrimaryKeySelective(productImage);
    }

    @Override
    public ProductImage get(int id) {
        ProductImage productImage = productImageMapper.selectByPrimaryKey(id);
        return productImage;
    }

    @Override
    public List<ProductImage> list(int pid, String type) {

        ProductImageExample productImageExample = new ProductImageExample();
        productImageExample.createCriteria()
                .andPidEqualTo(pid)
                .andTypeEqualTo(type);
        return productImageMapper.selectByExample(productImageExample);
    }
}
