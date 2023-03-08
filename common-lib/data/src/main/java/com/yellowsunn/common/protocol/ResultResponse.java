package com.yellowsunn.common.protocol;

public record ResultResponse<T>(
        T data,
        boolean success,
        String message
) {
    public static <T> ResultResponse<T> ok(T data) {
        return new ResultResponse<>(data, true, null);
    }

    public static ResultResponse<Void> fail(String message) {
        return new ResultResponse<>(null, false, message);
    }
}
