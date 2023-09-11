package org.G346.service;
import org.G346.pojo.Car;
import org.G346.mapper.CarMapper;
import org.G346.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarMapper carMapper;
    /**
     * 分页查找车辆
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public PageResult<Car> selectCar(int pageNumber, int pageSize){
        PageResult.startPage(pageNumber,pageSize);
        List<Car> cars = carMapper.selectAll();
        return PageResult.build(cars);
    }
    /**
     * 查找所有车辆
     * @return
     */
    public PageResult<Car> selectAll(){
        List<Car> cars = carMapper.selectAll();
        return PageResult.build(cars);
    }

    /**
     * 添加车辆
     * @param car
     * @return
     */
    public int insertCar(Car car){
        return carMapper.insert(car);
    }
    public Car selectByPrimaryKey(String id){

        return carMapper.selectByPrimaryKey(id);
    }
    public PageResult<Car> findCarById(String id){
        List<Car> cars = carMapper.findCarById(id);
        return PageResult.build(cars);
    }
    public int deleteByPrimaryKey(String id){
        return carMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKey(Car row){
        return carMapper.updateByPrimaryKey(row);
    }
}
