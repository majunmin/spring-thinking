package com.majm.demo;

import com.majm.demo.entity.Order;
import com.majm.demo.entity.OrderDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.PropertyMapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-06-09 21:24
 * @since
 */
public class PropertyMapperTest {


    @Test
    public void testPropertyMapper() {
        Order order = new Order();
        order.setId(1L);
        order.setStatus(1);
        order.setTotalAmout(BigDecimal.ONE);
        order.setUserId(100L);
        order.setCreateTime(LocalDateTime.now());

        PropertyMapper mapper = PropertyMapper.get();
        OrderDTO orderDto = new OrderDTO();
        mapper.from(order::getId).to(orderDto::setId);
        // 如果  order::getStatus 不为 null,执行  to 里面的动作
        mapper.from(order::getStatus).whenNonNull().to(orderDto::setStatus);
        mapper.from(order::getTotalAmout).to(orderDto::setTotalAmout);
        mapper.from(order::getCreateTime).as((ldt) -> {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return dateTimeFormatter.format(ldt);
        }).to(orderDto::setCreateTime);

        System.out.println(orderDto);

    }
}
