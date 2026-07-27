package com.bulbulustur.android.businesslayer.Core.DTO

data class SystemDescReturnRequestReasonDTO(
    val systemDescReturnRequestReasonId: Int,
    val employeeId: Int,
    val insertedDate: String,
    val statusId: Int,
    val content: String
)