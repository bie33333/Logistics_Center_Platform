package org.G346.service;
import org.G346.pojo.Warehouse;
import org.G346.pojo.Warehouse;


import org.G346.mapper.WarehouseMapper;

import org.G346.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WarehouseService {

    @Autowired
    private WarehouseMapper warehouseMapper;
    /**
     * 分页查找仓库
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public PageResult<Warehouse> selectWarehouse(int pageNumber, int pageSize){
        PageResult.startPage(pageNumber,pageSize);
        List<Warehouse> warehouses = warehouseMapper.selectAll();
        return PageResult.build(warehouses);
    }
    /**
     * 查找所有仓库
     * @return
     */
    public PageResult<Warehouse> selectAll(){
        List<Warehouse> warehouses = warehouseMapper.selectAll();
        return PageResult.build(warehouses);
    }
    /**
     * 新建仓库
     * @param warehouse
     * @return
     */
    public int insertWarehouse(Warehouse warehouse){
        return warehouseMapper.insert(warehouse);

    }
    public Warehouse selectByPrimaryKey(String id){

        return warehouseMapper.selectByPrimaryKey(id);
    }
    public PageResult<Warehouse> findWarehouseById(String id){
        List<Warehouse> warehouses = warehouseMapper.findWarehouseById(id);
        return PageResult.build(warehouses);
    }
    public int deleteByPrimaryKey(String id){
        
        return warehouseMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKey(Warehouse row){
        return warehouseMapper.updateByPrimaryKey(row);
    }
    
}
