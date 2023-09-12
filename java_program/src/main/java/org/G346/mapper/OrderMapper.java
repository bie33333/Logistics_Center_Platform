package org.G346.mapper;

import java.util.List;
import org.G346.pojo.Order;

public interface OrderMapper {
    int deleteByPrimaryKey(String id);

    int insert(Order row);

    Order selectByPrimaryKey(String id);

    List<Order> selectAll();
    List<Order> findOrderById(String id);
    List<Order> lookupOrder(Order order);
    int updateByPrimaryKey(Order row);
}