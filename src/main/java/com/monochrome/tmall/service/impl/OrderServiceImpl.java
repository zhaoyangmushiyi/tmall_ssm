package com.monochrome.tmall.service.impl;

import com.monochrome.tmall.mapper.OrderMapper;
import com.monochrome.tmall.pojo.Order;
import com.monochrome.tmall.pojo.OrderExample;
import com.monochrome.tmall.pojo.User;
import com.monochrome.tmall.service.OrderService;
import com.monochrome.tmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    UserService userService;

    @Override
    public void add(Order order) {
        orderMapper.insert(order);
    }

    @Override
    public void delete(int id) {
        orderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Order order) {
        orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public Order get(int id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Order> list() {

        OrderExample orderExample = new OrderExample();
        orderExample.setOrderByClause("id desc");
        List<Order> orders = orderMapper.selectByExample(orderExample);
        setUser(orders);
        return orders;
    }

    private void setUser(List<Order> orders) {
        for (Order order :
                orders) {
            setUser(order);
        }
    }

    private void setUser(Order order) {
        User user = userService.get(order.getUid());
        order.setUser(user);
    }
}
