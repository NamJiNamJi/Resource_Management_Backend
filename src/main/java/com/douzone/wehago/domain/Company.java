package com.douzone.wehago.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {
    private Integer copSeq;
    private String copRegNum;
    private String copName;
    private String copAdmin;
}
