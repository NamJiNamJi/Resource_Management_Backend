package com.douzone.wehago.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigInteger;
import java.util.Date;

@Getter
@Setter
@ToString
public class Car {
    private int carSeq;
    private String carNumber;
    private String carType;
    private String carImage;
    private Integer carYear;
    private int carDistance;
}

