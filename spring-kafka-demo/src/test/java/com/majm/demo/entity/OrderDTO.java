package com.majm.demo.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDTO {
    private Long id;

    private BigDecimal totalAmout;

    private Integer status;

    private Long userId;

    private String createTime;
}