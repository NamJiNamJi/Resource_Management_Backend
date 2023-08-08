package com.douzone.wehago.dto.user;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDTO {

    private Integer copSeq; // 회사 일련번호
    private String copRegNum; // 회사 사업자등록번호
    private String copName; // 회사명
    private Integer userSeq; // 회원 일련번호
    private String userId; // 회원 아이디
    private String userName; // 회원 이름
    private String userEmail; // 회원 이메일
    private Boolean userState; // 회원 상태
    private String userImage; // 회원 이미지
    private String empPosition; // 사원 직책
    private String authLevel; // 사원 권한 등급
    
}
