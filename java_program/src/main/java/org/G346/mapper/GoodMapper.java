package org.G346.mapper;

import java.util.List;
import org.G346.pojo.Good;

public interface GoodMapper {
    int deleteByPrimaryKey(String id);

    int insert(Good row);

    Good selectByPrimaryKey(String id);

    List<Good> selectAll();
    List<Good> findGoodById(String id);
    int updateByPrimaryKey(Good row);
}