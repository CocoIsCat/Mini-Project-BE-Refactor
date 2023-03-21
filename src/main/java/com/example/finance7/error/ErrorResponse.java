package com.example.finance7.error;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ErrorResponse {
    private int status;
    private String message;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public static ErrorResponse of(Messages message) {
        return new ErrorResponse(message.getStatus(), message.getMessage());
    }

    public static ErrorResponse of(String message) {
        return new ErrorResponse(400, message);
    }

}
