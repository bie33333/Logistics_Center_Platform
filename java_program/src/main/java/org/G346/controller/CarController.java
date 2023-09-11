package org.G346.controller;

import org.G346.pojo.*;
import org.G346.pojo.Car;
import org.G346.pojo.Car;
import org.G346.pojo.Car;
import org.G346.service.CarService;
import org.G346.utils.PageResult;
import org.G346.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class CarController {
    @Autowired
    CarService carService;

    /**
     * 分页查找所有车辆
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @RequestMapping("selectCar")
    public PageResult<Car> selectCar(String pageNumber, String pageSize) {
        return carService.selectCar(Integer.parseInt(pageNumber),Integer.parseInt(pageSize));
    }
    /**
     * 查找所有用户
     * @return
     */
    @RequestMapping("selectAllCar")
    public PageResult<Car> selectAllCar(){
        return carService.selectAll();
    }
    /**
     * 根据用户名查找单个车辆
     * @param id
     * @return
     */
    @RequestMapping("findCarById")
    public PageResult<Car> findCarById(String id){
        return carService.findCarById(id);
    }

    /**
     * 添加车辆
     * @param id
     * @param name
     * @param status
     * @param describe
     * @return
     */
    @RequestMapping("insertCar")
    public Result insertCar(String id,String name
            , String status, String describe){
        if(id == null || id.equals(""))
            return Result.error(400, "车辆id不能为空!", null);
        if(name == null || name.equals(""))
            return Result.error(400, "车辆名不能为空!", null);
        if(status == null || status.equals(""))
            return Result.error(400, "车辆状态不能为空!", null);
        if(describe == null || describe.equals(""))
            return Result.error(400, "车辆外观描述不能为空!", null);
        Car car = new Car();
        car.setId(id);
        car.setName(name);
        car.setStatus(Integer.parseInt(status));
        car.setDescribe(describe);

        if (carService.selectByPrimaryKey(id)!=null) {
            return Result.error(400,"添加车辆失败,车辆id已存在！");
        } else {
            int row = carService.insertCar(car);
            if (row > 0) {
                return Result.ok();
            } else {
                return Result.error(400, "插入失败！", null);
            }
        }
    }
    /**
     * 根据id删除车辆
     * @param id
     * @return
     */
    @RequestMapping("deleteCar")
    public Result deleteCar(String id){
        if(id == null || id.equals(""))
            return Result.error(400, "车牌号不能为空!", null);
        if (carService.selectByPrimaryKey(id)==null) {
            return Result.error(400,"删除失败，车辆不存在！");
        }else {
            carService.deleteByPrimaryKey(id);
            return Result.ok();
        }
    }

    /**
     * 更改车辆信息
     * @param id
     * @param name
     * @param status
     * @param describe
     * @return
     */
    @RequestMapping("updateCar")
    public Result updateUser(String id,String name
            , String status, String describe
    ) {
        if(id == null || id.equals(""))
            return Result.error(400, "车牌号不能为空!", null);
        Car car = carService.selectByPrimaryKey(id);
        if(car==null)
            return Result.error(400, "车辆不存在!", null);

        if(name!=null) car.setName(name);
        if(describe!=null)car.setDescribe(describe);
        if(status!=null)car.setStatus(Integer.parseInt(status));
        carService.updateByPrimaryKey(car);
        return Result.ok();


    }
    
    
}
