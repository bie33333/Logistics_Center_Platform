package org.G346;

import org.G346.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void test01() {
        System.out.println(userMapper.selectAll());
    }
}
