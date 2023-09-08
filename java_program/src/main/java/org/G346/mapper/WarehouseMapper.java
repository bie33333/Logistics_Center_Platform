package org.G346.mapper;

import java.util.List;
import org.G346.pojo.Warehouse;

public interface WarehouseMapper {
    int deleteByPrimaryKey(String id);

    int insert(Warehouse row);

    Warehouse selectByPrimaryKey(String id);

    List<Warehouse> selectAll();

    int updateByPrimaryKey(Warehouse row);
}