package sch.soonjomannam.soonjmannamfull.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TokenErrorCode implements ErrorCustom{

    TOKEN_NOT_VALID(1001, "유효하지 않는 토큰"),
    TOKEN_NOT_FOUND(1002, "토큰을 찾을 수 없음"),
    TOKEN_EXPIRED(1003, "토큰이 만료됨"),
    TOKEN_MALFORMED(1005, "형식이 잘못된 토큰"),
    TOKEN_SIGNATURE_INVALID(1006, "유효하지 않은 서명"),
    TOKEN_NOT_ALLOWED(1007, "토큰 권한 부족"),
    TOKEN_ALREADY_USED(1008, "이미 사용된 토큰"),
    TOKEN_INVALID_SCOPE(1009, "유효하지 않은 스코프"),
    TOKEN_REVOKED(1010, "토큰이 철회됨"),
    TOKEN_INVALID_CLAIM(1011, "유효하지 않은 클레임")
    ;

    private final int code;
    private final String description;
}
