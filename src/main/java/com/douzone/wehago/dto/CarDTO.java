package com.douzone.wehago.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CarDTO {
    private Integer cid;
    private String carName;
    private Integer carYear;
    private Integer carDistance;
    private Date startDate;
    private Date endDate;
    private String lender;
}
