package org.G346.mapper;

import java.util.List;
import org.G346.pojo.Order;

public interface OrderMapper {
    int deleteByPrimaryKey(String id);

    int insert(Order row);

    Order selectByPrimaryKey(String id);

    List<Order> selectAll();

    int updateByPrimaryKey(Order row);
}