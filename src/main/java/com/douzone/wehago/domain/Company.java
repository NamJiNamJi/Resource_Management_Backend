package com.douzone.wehago.domain;

import com.douzone.wehago.dto.CompanyDTO;
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

    public void update(CompanyDTO companyDTO) {
        this.copRegNum = companyDTO.getCopRegNum();
        this.copName = companyDTO.getCopName();
        this.copAdmin = companyDTO.getCopAdmin();
    }
}
