package org.G346.mapper;

import java.util.List;
import org.G346.pojo.Good;

public interface GoodMapper {
    int insert(Good row);

    List<Good> selectAll();
}