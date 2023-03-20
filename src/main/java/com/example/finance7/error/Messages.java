package com.example.finance7.error;

import lombok.Getter;

@Getter
public enum Messages {
    USER_NOT_FOUND(400, "존재하지 않는 사용자입니다."),
    PRODUCT_NOT_FOUND(400, "존재하지 않는 상품입니다."),
    ;

    private int status;
    private String message;
    Messages(int status, String message) {
        this.status = status;
        this.message = message;
    }


}
