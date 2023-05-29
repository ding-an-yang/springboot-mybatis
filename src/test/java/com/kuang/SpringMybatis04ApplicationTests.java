package com.kuang;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kuang.mapper.OrdersMapper;
import com.kuang.model.Orders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class SpringMybatis04ApplicationTests {

    @Autowired
    DataSource dataSource;
    @Autowired
    private OrdersMapper ordersMapper;
    @Test
    void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass());
        System.out.println(dataSource.getConnection());
    }

    @Test
    public void testSelect(){
        Orders orders2 = new Orders();
        orders2.setId(1);
        List<Orders> order = ordersMapper.selectList(new LambdaQueryWrapper<Orders>().isNotNull(Orders::getId));
        order.forEach(System.out::println);
        List<Orders> orders = ordersMapper.selectList(null);
        orders.forEach(System.out::println);

    }


}
