package com.bulbulustur.android.businesslayer.Core.DTO

data class SystemDescReturnRequestStatusDTO(
    val systemDescReturnRequestStatusId: Int,
    val employeeId: Int,
    val insertedDate: String,
    val statusId: Int,
    val content: String
)