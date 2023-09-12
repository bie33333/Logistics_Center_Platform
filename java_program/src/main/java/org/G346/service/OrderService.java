package org.G346.service;


import org.G346.mapper.OrderMapper;
import org.G346.pojo.Order;

import org.G346.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderMapper orderMapper;

    /**
     * 分页查找订单
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public PageResult<Order> selectOrder(int pageNumber, int pageSize){
        PageResult.startPage(pageNumber,pageSize);
        List<Order> orders = orderMapper.selectAll();
        return PageResult.build(orders);
    }

    public PageResult<Order> lookupOrder(int pageNumber, int pageSize, Order order){
        PageResult.startPage(pageNumber,pageSize);
        List<Order> orders = orderMapper.lookupOrder(order);
        return PageResult.build(orders);
    }
    public PageResult<Order> selectAll(){
        List<Order> orders = orderMapper.selectAll();
        return PageResult.build(orders);
    }
    public int insertOrder(Order order){
        return orderMapper.insert(order);
    }
    public Order selectByPrimaryKey(String id){

        return orderMapper.selectByPrimaryKey(id);
    }
    public PageResult<Order> findOrderById(String id){
        List<Order> orders = orderMapper.findOrderById(id);
        return PageResult.build(orders);
    }
    public int deleteByPrimaryKey(String id){
        return orderMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKey(Order row) {
        return orderMapper.updateByPrimaryKey(row);

    }
    
    
}
