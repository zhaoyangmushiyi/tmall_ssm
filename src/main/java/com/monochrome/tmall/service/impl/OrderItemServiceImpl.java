package com.monochrome.tmall.service.impl;

import com.monochrome.tmall.mapper.OrderItemMapper;
import com.monochrome.tmall.pojo.Order;
import com.monochrome.tmall.pojo.OrderItem;
import com.monochrome.tmall.pojo.OrderItemExample;
import com.monochrome.tmall.pojo.Product;
import com.monochrome.tmall.service.OrderItemService;
import com.monochrome.tmall.service.OrderService;
import com.monochrome.tmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    OrderItemMapper orderItemMapper;

    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    @Override
    public void add(OrderItem orderItem) {
        orderItemMapper.insert(orderItem);
    }

    @Override
    public void delete(int id) {
        orderItemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(OrderItem orderItem) {
        orderItemMapper.updateByPrimaryKeySelective(orderItem);
    }

    @Override
    public OrderItem get(int id) {
        OrderItem orderItem = orderItemMapper.selectByPrimaryKey(id);
        setProduct(orderItem);
        return orderItem;
    }

    private void setProduct(OrderItem orderItem) {
        Product product = productService.get(orderItem.getPid());
        orderItem.setProduct(product);
    }

    @Override
    public List<OrderItem> list() {
        OrderItemExample orderItemExample = new OrderItemExample();
        orderItemExample.setOrderByClause("id desc");
        List<OrderItem> orderItems = orderItemMapper.selectByExample(orderItemExample);
        return orderItems;
    }

    public void setProduct(List<OrderItem> orderItems) {
        for (OrderItem orderItem :
                orderItems) {
            setProduct(orderItem);
        }
    }

    @Override
    public void fill(List<Order> orders) {
        for (Order order :
                orders) {
            fill(order);
        }
    }

    @Override
    public void fill(Order order) {
        OrderItemExample orderItemExample = new OrderItemExample();
        orderItemExample.createCriteria().andOidEqualTo(order.getId());
        orderItemExample.setOrderByClause("id desc");
        List<OrderItem> orderItems = orderItemMapper.selectByExample(orderItemExample);
        setProduct(orderItems);

        float total = 0;
        int totalNumber = 0;
        for (OrderItem orderItem :
                orderItems) {
            total += orderItem.getNumber() * orderItem.getProduct().getPromotePrice();
            totalNumber += orderItem.getNumber();
        }
        order.setTotal(total);
        order.setTotalNumber(totalNumber);
        order.setOrderItems(orderItems);
    }

    @Override
    public int getSaleCount(int pid) {
        OrderItemExample orderItemExample = new OrderItemExample();
        orderItemExample.createCriteria().andPidEqualTo(pid);
        List<OrderItem> orderItems = orderItemMapper.selectByExample(orderItemExample);
        int result = 0;
        for (OrderItem orderItem :
                orderItems) {
            result += orderItem.getNumber();
        }
        return result;
    }

    @Override
    public List<OrderItem> listByUser(int uid) {
        OrderItemExample orderItemExample = new OrderItemExample();
        orderItemExample.createCriteria().andUidEqualTo(uid).andOidIsNull();
        List<OrderItem> orderItems = orderItemMapper.selectByExample(orderItemExample);
        setProduct(orderItems);
        return orderItems;
    }
}
