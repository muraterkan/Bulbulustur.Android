package com.bulbulustur.android.businesslayer.Core.DTO

data class SystemDescContactRequestStatusDTO(
    val systemDescContactRequestStatusId: Int,
    val employeeId: Int,
    val insertedDate: String,
    val statusId: Int,
    val code: String,
    val content: String,
    val displayOrder: Int
)