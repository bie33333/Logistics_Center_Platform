package org.G346.controller;

import org.G346.pojo.*;
import org.G346.pojo.Good;
import org.G346.pojo.Good;
import org.G346.pojo.Good;
import org.G346.service.GoodService;
import org.G346.service.WarehouseService;
import org.G346.utils.PageResult;
import org.G346.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class GoodController {
    @Autowired
    GoodService goodService;
    @Autowired
    WarehouseService warehouseService;

    /**
     * 分页查找所有物品
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @RequestMapping("selectGood")
    public PageResult<Good> selectGood(String pageNumber, String pageSize) {
        return goodService.selectGood(Integer.parseInt(pageNumber), Integer.parseInt(pageSize));
    }

    /**
     * 查找所有物品
     *
     * @return
     */
    @RequestMapping("selectAllGood")
    public PageResult<Good> selectAllGood() {
        return goodService.selectAll();
    }

    /**
     * 根据用户名查找单个物品
     *
     * @param id
     * @return
     */
    @RequestMapping("findGoodById")
    public PageResult<Good> findGoodById(String id) {
        return goodService.findGoodById(id);
    }

    /**
     * 添加物品
     *
     * @param id
     * @param name
     * @param warehouse
     * @param number
     * @return
     */
    @RequestMapping("insertGood")
    public Result insertGood(String id, String name
            , String warehouse, String number, String good_describe) {
        if (id == null || id.equals(""))
            return Result.error(400, "物品id不能为空!", null);
        if (name == null || name.equals(""))
            return Result.error(400, "物品名不能为空!", null);
        if (warehouse == null || warehouse.equals(""))
            return Result.error(400, "物品所在仓库不能为空!", null);
        if (number == null || number.equals(""))
            return Result.error(400, "物品数量不能为空!", null);

        Good good = new Good();
        good.setId(id);
        good.setName(name);
        good.setWarehouse(warehouse);
        good.setNumber(Integer.parseInt(number));
        good.setGood_describe(good_describe);
        if (goodService.selectByPrimaryKey(id) != null) {
            return Result.error(400, "添加物品失败,物品id已存在！");
        } else {
            if (warehouseService.selectByPrimaryKey(warehouse) == null) {

                return Result.error(400, "插入失败，仓库名不存在！", null);
            } else {int row = goodService.insertGood(good);
                if (row > 0) {
                    return Result.ok();
                } else {
                    return Result.error(400, "插入失败！", null);
                }
            }

        }
    }

    /**
     * 删除物品
     *
     * @param id
     * @return
     */
    @RequestMapping("deleteGood")
    public Result deleteGood(String id) {
        if (id == null || id.equals(""))
            return Result.error(400, "物品id不能为空!", null);
        if (goodService.selectByPrimaryKey(id) == null) {
            return Result.error(400, "删除失败，物品不存在！");
        } else {
            goodService.deleteByPrimaryKey(id);
            return Result.ok();
        }
    }

    @RequestMapping("updateGood")
    public Result updateUser(String id, String name
            , String warehouse, String number, String good_describe
    ) {
        if (id == null || id.equals(""))
            return Result.error(400, "物品id不能为空!", null);
        Good good = goodService.selectByPrimaryKey(id);
        if (good == null)
            return Result.error(400, "物品不存在!", null);

        if (name != null) good.setName(name);
        if (warehouse != null) {
            if (warehouseService.selectByPrimaryKey(warehouse) == null)
                return Result.error(400, "仓库不存在!", null);
            else good.setWarehouse(warehouse);
        }
        if (number != null) good.setNumber(Integer.parseInt(number));
        if(good_describe!=null) good.setGood_describe(good_describe);
        goodService.updateByPrimaryKey(good);
        return Result.ok();


    }


}