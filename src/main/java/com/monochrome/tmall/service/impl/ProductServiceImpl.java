package com.monochrome.tmall.service.impl;

import com.monochrome.tmall.mapper.ProductMapper;
import com.monochrome.tmall.pojo.Category;
import com.monochrome.tmall.pojo.Product;
import com.monochrome.tmall.pojo.ProductExample;
import com.monochrome.tmall.pojo.ProductImage;
import com.monochrome.tmall.service.CategoryService;
import com.monochrome.tmall.service.ProductImageService;
import com.monochrome.tmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapper productMapper;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductImageService productImageService;

    @Override
    public void add(Product product) {
        productMapper.insert(product);
    }

    @Override
    public void delete(int id) {
        productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Product product) {
        productMapper.updateByPrimaryKeySelective(product);
    }

    @Override
    public Product get(int id) {
        Product product = productMapper.selectByPrimaryKey(id);
        setCategory(product);
        return product;
    }

    public void setCategory(List<Product> products) {
        for (Product product :
                products) {
            setCategory(product);
        }
    }

    public void setCategory(Product product) {
        int cid = product.getCid();
        Category category = categoryService.get(cid);
        product.setCategory(category);
    }

    @Override
    public List<Product> list(int cid) {
        ProductExample productExample = new ProductExample();
        productExample.createCriteria().andCidEqualTo(cid);
        productExample.setOrderByClause("id desc");
        List<Product> products = productMapper.selectByExample(productExample);
        setCategory(products);
        setFirstProductImage(products);
        return products;
    }

    @Override
    public void setFirstProductImage(Product product) {
        List<ProductImage> pis = productImageService.list(product.getId(), productImageService.type_single);
        if (!pis.isEmpty()) {
            ProductImage productImage = pis.get(0);
            product.setFirstProductImage(productImage);
        }
    }

    @Override
    public void fill(List<Category> categories) {
        for (Category category :
                categories) {
            fill(category);
        }
    }

    @Override
    public void fill(Category category) {
        List<Product> products = list(category.getId());
        category.setProducts(products);
    }

    @Override
    public void fillByRow(List<Category> categories) {
        int productNumberEachRow = 8;
        for (Category category :
                categories) {
            List<Product> products = category.getProducts();
            List<List<Product>> productsByRow = new ArrayList<>();
            for (int i = 0; i < products.size(); i += productNumberEachRow) {
                int size = i + productNumberEachRow;
                size = size > products.size() ? products.size() : size;
                List<Product> productsOfEachRow = products.subList(i, size);
                productsByRow.add(productsOfEachRow);
            }
            category.setProductsByRow(productsByRow);
        }
    }

    public void setFirstProductImage(List<Product> products) {
        for (Product product : products) {
            setFirstProductImage(product);
        }
    }
}
