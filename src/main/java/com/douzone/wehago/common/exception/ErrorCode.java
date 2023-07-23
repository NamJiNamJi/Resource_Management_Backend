package com.douzone.wehago.common.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE(400, "INVALID_INPUT_VALUE", "잘못된 입력값 입니다."),
    METHOD_NOT_ALLOWED(405, "METHOD_NOT_ALLOWED", " 허용되지 않은 메서드 입니다."),
    ENTITY_NOT_FOUND(400, "ENTITY_NOT_FOUND", "개체를 찾을 수 없습니다."),
    INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVER_ERROR", "내부 서버 에러"),
    INVALID_TYPE_VALUE(400, "INVALID_TYPE_VALUE", "잘못된 유형 값"),
    HANDLE_ACCESS_DENIED(403, "HANDLE_ACCESS_DENIED", "접근 권한이 없습니다."),

    // Company
    COMPANY_NOT_EXIST(404, "COMPANY_NOT_EXIST", "회사가 존재하지 않습니다."),
    ;



    private final int status;
    private final String code;
    private final String message;

}
