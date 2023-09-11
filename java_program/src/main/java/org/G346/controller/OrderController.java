package org.G346.controller;



import org.G346.pojo.Order;
import org.G346.pojo.Order;
import org.G346.pojo.Order;
import org.G346.pojo.Order;
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

    @RequestMapping("selectOrder")
    public PageResult<Order> selectOrder(String pageNumber, String pageSize) {
        return orderService.selectOrder(Integer.parseInt(pageNumber), Integer.parseInt(pageSize));
    }
    @RequestMapping("selectAllOrder")
    public PageResult<Order> selectAllOrder() {
        return orderService.selectAll();
    }
    @RequestMapping("findOrderById")
    public PageResult<Order> findOrderById(String id) {
        return orderService.findOrderById(id);
    }

/**
    @RequestMapping("insertOrder")
    public Result insertOrder(String id, String userName, String userPhone, String goodId, String goodName
            , String carId, String carName, String addressee, String addressPhone, String address
            , String orderDescribe, String orderStatus, String price) {
        if (id == null) {
            return Result.error(400, "id不能为空!", null);
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
        order.setUserName(userName);
        order.setUserPhone(userPhone);
        order.setGoodId(goodId);
        order.setGoodName(goodName);
        order.setCarId(carId);
        order.setCarName(carName);
        order.setAddressee(addressee);
        order.setAddressPhone(addressPhone);
        order.setAddress(address);
        order.setOrderDescribe(orderDescribe);
        order.setOrderStatus(Integer.parseInt(orderStatus));

        order.setPrice(BigDecimal.valueOf(Double.parseDouble(price)));
        System.out.println(order.getPrice());
        if (orderService.selectByPrimaryKey(id) != null) {
            return Result.error(400, "添加订单失败,订单id已存在！");
        } else {
            if (warehouseService.selectByPrimaryKey(warehouse) == null) {

                return Result.error(400, "插入失败，仓库名不存在！", null);
            } else {int row = orderService.insertOrder(order);
                if (row > 0) {
                    return Result.ok();
                } else {
                    return Result.error(400, "插入失败！", null);
                }
            }

        }
    }
    **/
}
