package com.monochrome.tmall.service.impl;

import com.monochrome.tmall.mapper.PropertyValueMapper;
import com.monochrome.tmall.pojo.Product;
import com.monochrome.tmall.pojo.Property;
import com.monochrome.tmall.pojo.PropertyValue;
import com.monochrome.tmall.pojo.PropertyValueExample;
import com.monochrome.tmall.service.ProductService;
import com.monochrome.tmall.service.PropertyService;
import com.monochrome.tmall.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyValueServiceImpl implements PropertyValueService {

    @Autowired
    PropertyService propertyService;

    @Autowired
    PropertyValueMapper propertyValueMapper;

    @Override
    public void init(Product product) {
        List<Property> properties = propertyService.list(product.getCid());
        for (Property property :
                properties) {
            PropertyValue propertyValue = get(property.getId(), product.getId());
            if (propertyValue == null) {
                propertyValue = new PropertyValue();
                propertyValue.setPid(product.getId());
                propertyValue.setPtid(property.getId());
                propertyValueMapper.insert(propertyValue);
            }
        }
    }

    @Override
    public void update(PropertyValue propertyValue) {
        propertyValueMapper.updateByPrimaryKeySelective(propertyValue);
    }

    @Override
    public PropertyValue get(int ptid, int pid) {
        PropertyValueExample example = new PropertyValueExample();
        example.createCriteria()
                .andPtidEqualTo(ptid)
                .andPidEqualTo(pid);
        List<PropertyValue> propertyValues = propertyValueMapper.selectByExample(example);
        if (propertyValues.isEmpty()) {
            return null;
        }
        return propertyValues.get(0);
    }

    @Override
    public List<PropertyValue> list(int pid) {
        PropertyValueExample example = new PropertyValueExample();
        example.createCriteria().andPidEqualTo(pid);
        List<PropertyValue> propertyValues = propertyValueMapper.selectByExample(example);
        for (PropertyValue propertyValue :
                propertyValues) {
            Property property = propertyService.get(propertyValue.getPtid());
            propertyValue.setProperty(property);
        }
        return propertyValues;
    }
}
