package org.G346.mapper;

import java.util.List;
import org.G346.pojo.Car;

public interface CarMapper {
    int deleteByPrimaryKey(String id);

    int insert(Car row);

    Car selectByPrimaryKey(String id);
    List<Car> findCarById(String id);
    List<Car> selectAll();
    List<Car> lookupCar(Car car);

    int updateByPrimaryKey(Car row);


}