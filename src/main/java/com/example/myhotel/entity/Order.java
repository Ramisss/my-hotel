package com.example.myhotel.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;


@RequiredArgsConstructor
@Data
public class Order {
    private Integer id;
    private Integer userId;
    private Integer roomId;
    private Date checkIn;
    private Date checkOut;
    private Double price;
    private Byte rate;
    private Date orderDate;
    private boolean isOrdered;
    


}
