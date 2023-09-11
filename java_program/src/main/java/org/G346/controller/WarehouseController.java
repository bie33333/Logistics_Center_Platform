package org.G346.controller;


import org.G346.pojo.*;
import org.G346.pojo.Warehouse;
import org.G346.pojo.Warehouse;
import org.G346.pojo.Warehouse;
import org.G346.service.WarehouseService;
import org.G346.utils.PageResult;
import org.G346.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class WarehouseController {
    @Autowired
    WarehouseService warehouseService;
    /**
     * 分页查找所有仓库
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @RequestMapping("selectWarehouse")
    public PageResult<Warehouse> selectWarehouse(String pageNumber, String pageSize) {
        return warehouseService.selectWarehouse(Integer.parseInt(pageNumber),Integer.parseInt(pageSize));
    }
    /**
     * 查找所有车辆
     * @return
     */
    @RequestMapping("selectAllWarehouse")
    public PageResult<Warehouse> selectAllWarehouse(){
        return warehouseService.selectAll();
    }

    /**
     * 根据id查找单个仓库
     * @param id
     * @return
     */
    @RequestMapping("findWarehouseById")
    public PageResult<Warehouse> findWarehouseById(String id){
        return warehouseService.findWarehouseById(id);
    }
    /**
     * 添加仓库

     * @return
     */
    @RequestMapping("insertWarehouse")
    public Result insertWarehouse(String id, String capacity,String storage){
        if(id == null || id.equals(""))
            return Result.error(400, "仓库id不能为空!", null);

        Warehouse warehouse = new Warehouse();
        warehouse.setId(id);
        if(capacity == null || capacity.equals("")) warehouse.setCapacity(0);
        else  warehouse.setCapacity(Integer.parseInt(capacity));
        if(storage==null||storage.equals("")) warehouse.setStorage(0);
        else warehouse.setStorage(Integer.parseInt(storage));


        if (warehouseService.selectByPrimaryKey(id)!=null) {
            return Result.error(400,"添加仓库失败,仓库id已存在！");
        } else {
            int row = warehouseService.insertWarehouse(warehouse);
            if (row > 0) {
                return Result.ok();
            } else {
                return Result.error(400, "插入失败！", null);
            }
        }
    }

    /**
     * 删除仓库
     * @param id
     * @return
     */
    @RequestMapping("deleteWarehouse")
    public Result deleteWarehouse(String id){
        if(id == null || id.equals(""))
            return Result.error(400, "仓库id不能为空!", null);
        if (warehouseService.selectByPrimaryKey(id)==null) {
            return Result.error(400,"删除失败，仓库不存在！");
        }else {
            warehouseService.deleteByPrimaryKey(id);
            return Result.ok();
        }
    }
    
    @RequestMapping("updateWarehouse")
    public Result updateUser(String id
            , String storage, String capacity
    ) {
        if(id == null || id.equals(""))
            return Result.error(400, "仓库id不能为空!", null);
        Warehouse warehouse = warehouseService.selectByPrimaryKey(id);
        if(warehouse==null)
            return Result.error(400, "车辆不存在!", null);

        if(storage!=null) warehouse.setStorage(Integer.parseInt(storage));
        if(capacity!=null)warehouse.setCapacity(Integer.parseInt(capacity));
        
        warehouseService.updateByPrimaryKey(warehouse);
        return Result.ok();


    }

}
