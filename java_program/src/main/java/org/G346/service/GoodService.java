package org.G346.service;

import org.G346.pojo.*;
import org.G346.pojo.Good;
import org.G346.pojo.Good;
import org.G346.pojo.Good;
import org.G346.mapper.GoodMapper;
import org.G346.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodService {
    @Autowired
    private GoodMapper goodMapper;

    /**
     * 分页查找物品
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public PageResult<Good> selectGood(int pageNumber, int pageSize){
        PageResult.startPage(pageNumber,pageSize);
        List<Good> goods = goodMapper.selectAll();
        return PageResult.build(goods);
    }
    /**
     * 查找所有物品
     * @return
     */
    public PageResult<Good> selectAll(){
        List<Good> goods = goodMapper.selectAll();
        return PageResult.build(goods);
    }
    /**
     * 添加物品
     * @param good
     * @return
     */
    public int insertGood(Good good){
        return goodMapper.insert(good);
    }
    
    public Good selectByPrimaryKey(String id){

        return goodMapper.selectByPrimaryKey(id);
    }
    public PageResult<Good> findGoodById(String id){
        List<Good> goods = goodMapper.findGoodById(id);
        return PageResult.build(goods);
    }
    public int deleteByPrimaryKey(String id){
        return goodMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKey(Good row){
        return goodMapper.updateByPrimaryKey(row);
    }

}
