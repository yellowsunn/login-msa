//package com.yellowsunn.userservice.api
//
//data class ResultResponse<T>(
//    val data: T?,
//    val success: Boolean,
//    val message: String?,
//) {
//    companion object {
//        fun <T> ok(data: T?): ResultResponse<T> =
//            ResultResponse(
//                data = data,
//                success = true,
//                message = null,
//            )
//
//        fun fail(message: String?): ResultResponse<Unit> =
//            ResultResponse(
//                data = null,
//                success = false,
//                message = message,
//            )
//    }
//}
