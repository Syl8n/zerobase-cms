package com.zerobase.cms.zerobasecms.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    ALREADY_REGISTER_ACCOUNT(HttpStatus.BAD_REQUEST, "이미 가입된 회원입니다."),
    ACCOUNT_NOT_FOUND(HttpStatus.BAD_REQUEST, "회원 정보를 찾을 수 없습니다.")

    ;
    private final HttpStatus httpStatus;
    private final String detail;
}
