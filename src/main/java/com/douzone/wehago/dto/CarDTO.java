package com.douzone.wehago.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.Date;

@Getter
@Setter
public class CarDTO {
    private int car_seq;
    private String car_number;
    private String car_type;
    private String car_image;
    private Integer car_year;
    private int car_distance;
}
