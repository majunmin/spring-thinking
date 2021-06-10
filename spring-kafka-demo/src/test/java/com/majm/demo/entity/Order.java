package com.majm.demo.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Order {
    private Long id;

    private BigDecimal totalAmout;

    private Integer status;

    private Long userId;

    private LocalDateTime createTime;
}