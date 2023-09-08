package org.G346.utils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class PageResult<T> implements Serializable {
    private Long pageTotal;
    private List<T> list;

    public static <H> Page<H> startPage(int pageNumber,int pageSize){
        return PageHelper.startPage(pageNumber,pageSize);
    }

    public static <H> PageResult<H> build(List<H> lists){
        PageResult<H> pageResult = new PageResult<H>();
        PageInfo<H> pageInfo = new PageInfo<>(lists);
        pageResult.setList(pageInfo.getList());
        pageResult.setPageTotal(pageInfo.getTotal());
        return pageResult;
    }
}
