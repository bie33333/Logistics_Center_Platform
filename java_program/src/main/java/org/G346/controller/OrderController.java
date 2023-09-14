package org.G346.controller;


import org.G346.pojo.*;
import org.G346.pojo.Order;
import org.G346.pojo.Order;
import org.G346.service.CarService;
import org.G346.service.GoodService;
import org.G346.service.UserService;
import org.G346.utils.PageResult;
import org.G346.utils.Result;
import org.G346.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Objects;

@RestController
@CrossOrigin
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;
    @Autowired
    GoodService goodService;
    @Autowired
    CarService carService;


    @RequestMapping("selectOrder")
    public PageResult<Order> selectOrder(String pageNumber, String pageSize) {
        return orderService.selectOrder(Integer.parseInt(pageNumber), Integer.parseInt(pageSize));
    }

    @RequestMapping("selectAllOrder")
    public PageResult<Order> selectAllOrder() {
        return orderService.selectAll();
    }

    @RequestMapping("lookupOrder")
    public PageResult<Order> lookupOrder(String pageNumber, String pageSize
            , String id, String orderStatus) {
        Order order = new Order();
        order.setId(id);
        if (orderStatus == null || "".equals(orderStatus)) {
            order.setOrderStatus(-1);
        } else {
            order.setOrderStatus(Integer.parseInt(orderStatus));
        }
        return orderService.lookupOrder(Integer.parseInt(pageNumber), Integer.parseInt(pageSize), order);
    }

    @RequestMapping("findOrderById")
    public PageResult<Order> findOrderById(String id) {
        return orderService.findOrderById(id);
    }


    @RequestMapping("insertOrder")
    public Result insertOrder(String id, String userAccount, String goodId, String goodNumber
            , String carId, String addressee, String addressPhone, String address
            , String orderDescribe, String price) {
        if (id == null) {
            return Result.error(400, "id不能为空!", null);
        }
        if (userAccount == null) {
            return Result.error(400, "userAccount不能为空!", null);
        }
        if (goodId == null) {
            return Result.error(400, "goodId不能为空!", null);
        }
        if (goodNumber == null) {
            return Result.error(400, "goodNumber不能为空!", null);
        }
        if (carId == null) {
            return Result.error(400, "carId不能为空!", null);
        }
        if (addressee == null) {
            return Result.error(400, "addressee不能为空!", null);
        }
        if (addressPhone == null) {
            return Result.error(400, "addressPhone不能为空!", null);
        }
        if (address == null) {
            return Result.error(400, "address不能为空!", null);
        }
        if (orderDescribe == null) {
            return Result.error(400, "orderDescribe不能为空!", null);
        }
        if (price == null) {
            return Result.error(400, "price不能为空!", null);
        }

        Order order = new Order();

        if (orderService.selectByPrimaryKey(id) != null) {
            return Result.error(400, "添加订单失败,订单id已存在！");
        }
        order.setId(id);

        User user = userService.selectByPrimaryKey(userAccount);
        if (user == null) {
            return Result.error(400, "添加失败，用户不存在！", null);
        } else {
            order.setUserAccount(user.getUserAccount());
            order.setUserName(user.getUserName());
            order.setUserPhone(user.getUserPhone());
        }
        Good good = goodService.selectByPrimaryKey(goodId);
        if (good == null) {
            return Result.error(400, "插入失败，物品不存在！", null);
        }
        if (Integer.parseInt(goodNumber) > good.getNumber()) {
            return Result.error(400, "插入失败，剩余物品数量不足！");
        } else {
            good.setNumber(good.getNumber() - Integer.parseInt(goodNumber));
            goodService.updateByPrimaryKey(good);
            order.setGoodId(good.getId());
            order.setGoodNumber(Integer.parseInt(goodNumber));
            order.setGoodName(good.getName());
        }

        Car car = carService.selectByPrimaryKey(carId);
        if (car == null) {
            return Result.error(400, "插入失败，车辆不存在！", null);
        }
        if (car.getStatus()==1||car.getStatus()==3) {
            return Result.error(400,"车辆已被占用！",null);
        } else {
            car.setStatus(1);
            carService.updateByPrimaryKey(car);
            order.setCarId(car.getId());
            order.setCarName(car.getName());
        }
        order.setOrderStatus(1);
        order.setAddressee(addressee);
        order.setAddressPhone(addressPhone);
        order.setAddress(address);
        order.setOrderDescribe(orderDescribe);
        order.setPrice(BigDecimal.valueOf(Double.parseDouble(price)));
        //System.out.println(order.getPrice());
        int row = orderService.insertOrder(order);
        if (row > 0) {
            return Result.ok();
        } else {
            return Result.error(400, "插入失败！", null);
        }
    }

    /**
     * 完成订单，将orderStatus修改为0（已完成），carStatus修改为2（空闲中）
     * @param id
     * @return
     */
    @RequestMapping("completeOrder")
    public Result completeOrder(String id){
        Order order = orderService.selectByPrimaryKey(id);
        if (id == null || id.equals(""))
            return Result.error(400, "id不能为空!", null);
        if (order == null) {
            return Result.error(400, "修改失败，订单不存在！");
        }else {
            Car car = carService.selectByPrimaryKey(order.getCarId());
            order.setOrderStatus(0);
            car.setStatus(2);
            orderService.updateByPrimaryKey(order);
            carService.updateByPrimaryKey(car);
            return Result.ok();
        }
    }
    @RequestMapping("deleteOrder")
    public Result deleteOrder(String id) {
        Order order = orderService.selectByPrimaryKey(id);

        if (id == null || id.equals(""))
            return Result.error(400, "id不能为空!", null);
        if (order == null) {
            return Result.error(400, "删除失败，订单不存在！");
        } else {
            orderService.deleteByPrimaryKey(id);

            return Result.ok();
        }
    }

    /**
     *修改订单信息
     * @param id
     * @param addressee
     * @param addressPhone
     * @param address
     * @param orderDescribe
     * @param price
     * @return
     */
    @RequestMapping("updateOrder")
    public Result updateOrder(String id,  String addressee, String addressPhone, String address
            , String orderDescribe, String price) {
        if (id == null) {
            return Result.error(400, "id不能为空!", null);
        }
        if (addressee == null) {
            return Result.error(400, "addressee不能为空!", null);
        }
        if (addressPhone == null) {
            return Result.error(400, "addressPhone不能为空!", null);
        }
        if (address == null) {
            return Result.error(400, "address不能为空!", null);
        }
        if (orderDescribe == null) {
            return Result.error(400, "orderDescribe不能为空!", null);
        }
        if (price == null) {
            return Result.error(400, "price不能为空!", null);
        }

        Order order = orderService.selectByPrimaryKey(id);
        if (order == null) {
            return Result.error(400, "修改失败，订单不存在！", null);
        }

        order.setAddressee(addressee);
        order.setAddressPhone(addressPhone);
        order.setAddress(address);
        order.setOrderDescribe(orderDescribe);
        order.setPrice(BigDecimal.valueOf(Double.parseDouble(price)));
        orderService.updateByPrimaryKey(order);
        return Result.ok();
    }

}
