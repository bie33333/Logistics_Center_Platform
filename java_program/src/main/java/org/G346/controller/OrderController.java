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
            , String id, String orderStatus){
        Order order = new Order();
        order.setId(id);
        if(orderStatus==null){
            order.setOrderStatus(-1);
        }else {
            order.setOrderStatus(Integer.parseInt(orderStatus));
        }
        return orderService.lookupOrder(Integer.parseInt(pageNumber),Integer.parseInt(pageSize),order);
    }
    @RequestMapping("findOrderById")
    public PageResult<Order> findOrderById(String id) {
        return orderService.findOrderById(id);
    }


    @RequestMapping("insertOrder")
    public Result insertOrder(String id, String userAccount, String userName, String userPhone, String goodId,String goodNumber, String goodName
            , String carId, String carName, String addressee, String addressPhone, String address
            , String orderDescribe, String orderStatus, String price) {
        if (id == null) {
            return Result.error(400, "id不能为空!", null);
        }
        if (userAccount == null) {
            return Result.error(400, "userAccount不能为空!", null);
        }
        if (userName == null) {
            return Result.error(400, "userName不能为空!", null);
        }
        if (userPhone == null) {
            return Result.error(400, "userPhone不能为空!", null);
        }
        if (goodId == null) {
            return Result.error(400, "goodId不能为空!", null);
        }
        if (goodNumber == null) {
            return Result.error(400, "goodNumber不能为空!", null);
        }
        if (goodName == null) {
            return Result.error(400, "goodName不能为空!", null);
        }
        if (carId == null) {
            return Result.error(400, "carId不能为空!", null);
        }
        if (carName == null) {
            return Result.error(400, "carName不能为空!", null);
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
        if (orderStatus == null) {
            return Result.error(400, "orderStatus不能为空!", null);
        }
        if (price == null) {
            return Result.error(400, "price不能为空!", null);
        }

        Order order = new Order();
        order.setId(id);
        order.setUserAccount(userAccount);
        order.setUserName(userName);
        order.setUserPhone(userPhone);
        order.setGoodId(goodId);
        order.setGoodNumber(Integer.parseInt(goodNumber));
        order.setGoodName(goodName);
        order.setCarId(carId);
        order.setCarName(carName);
        order.setAddressee(addressee);
        order.setAddressPhone(addressPhone);
        order.setAddress(address);
        order.setOrderDescribe(orderDescribe);
        order.setOrderStatus(Integer.parseInt(orderStatus));
        order.setPrice(BigDecimal.valueOf(Double.parseDouble(price)));
        //System.out.println(order.getPrice());

        if (orderService.selectByPrimaryKey(id) != null) {
            return Result.error(400, "添加订单失败,订单id已存在！");
        }
        User user = userService.selectByPrimaryKey(userAccount);
        if (user == null) {
            return Result.error(400, "插入失败，用户不存在！", null);
        } else if (user.getUserName()!=userName||user.getUserPhone()!=userPhone) {
            return Result.error(400,"用户姓名或电话错误！",null);
        }
        Good good= goodService.selectByPrimaryKey(goodId);
        if (good == null) {
            return Result.error(400, "插入失败，物品不存在！", null);
        } else if (good.getName()!=goodName) {
            return Result.error(400,"物品名错误！",null);
        }
        Car car =carService.selectByPrimaryKey(carId);
        if (car == null) {
            return Result.error(400, "插入失败，车辆不存在！", null);
        } else if (car.getName()!=carName) {
            return Result.error(400,"车辆名错误！",null);
        }

        int row = orderService.insertOrder(order);
        if (row > 0) {
            return Result.ok();
        } else {
            return Result.error(400, "插入失败！", null);
        }
    }


    @RequestMapping("deleteOrder")
    public Result deleteOrder(String id){
        if(id == null || id.equals(""))
            return Result.error(400, "id不能为空!", null);
        if (orderService.selectByPrimaryKey(id)==null) {
            return Result.error(400,"删除失败，订单不存在！");
        }else {
            orderService.deleteByPrimaryKey(id);
            return Result.ok();
        }
    }
    @RequestMapping("updateOrder")
    public Result updateOrder(String id, String userAccount, String userName, String userPhone, String goodId,String goodNumber, String goodName
            , String carId, String carName, String addressee, String addressPhone, String address
            , String orderDescribe, String orderStatus, String price) {
        if (id == null) {
            return Result.error(400, "id不能为空!", null);
        }
        if (userAccount == null) {
            return Result.error(400, "userAccount不能为空!", null);
        }
        if (userName == null) {
            return Result.error(400, "userName不能为空!", null);
        }
        if (userPhone == null) {
            return Result.error(400, "userPhone不能为空!", null);
        }
        if (goodId == null) {
            return Result.error(400, "goodId不能为空!", null);
        }
        if (goodNumber == null) {
            return Result.error(400, "goodNumber不能为空!", null);
        }
        if (goodName == null) {
            return Result.error(400, "goodName不能为空!", null);
        }
        if (carId == null) {
            return Result.error(400, "carId不能为空!", null);
        }
        if (carName == null) {
            return Result.error(400, "carName不能为空!", null);
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
        if (orderStatus == null) {
            return Result.error(400, "orderStatus不能为空!", null);
        }
        if (price == null) {
            return Result.error(400, "price不能为空!", null);
        }

        Order order = orderService.selectByPrimaryKey(id);

        User user = userService.selectByPrimaryKey(userAccount);
        if (user == null) {
            return Result.error(400, "修改失败，用户不存在！", null);
        } else if (user.getUserName()!=userName||user.getUserPhone()!=userPhone) {
            return Result.error(400,"用户姓名或电话错误！",null);
        }else {
            order.setUserAccount(userAccount);
            order.setUserName(userName);
            order.setUserPhone(userPhone);
        }

        Good good= goodService.selectByPrimaryKey(goodId);
        if (good == null) {
            return Result.error(400, "修改失败，物品不存在！", null);
        } else if (good.getName()!=goodName) {
            return Result.error(400,"物品名错误！",null);
        }else {
            order.setGoodId(goodId);
            order.setGoodName(goodName);
        }

        Car car =carService.selectByPrimaryKey(carId);
        if (car == null) {
            return Result.error(400, "修改失败，车辆不存在！", null);
        } else if (car.getName()!=carName) {
            return Result.error(400,"车辆名错误！",null);
        }else {
            order.setCarId(carId);
            order.setCarName(carName);
        }
        order.setAddressee(addressee);
        order.setAddressPhone(addressPhone);
        order.setAddress(address);
        order.setOrderDescribe(orderDescribe);
        order.setGoodNumber(Integer.parseInt(goodNumber));
        order.setOrderStatus(Integer.parseInt(orderStatus));
        order.setPrice(BigDecimal.valueOf(Double.parseDouble(price)));

        int row = orderService.updateByPrimaryKey(order);

        return Result.ok();


    }

}
