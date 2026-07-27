package com.bulbulustur.android.businesslayer.Core.DTO

data class SystemDescComplaintStatusDTO(
    val systemDescComplaintStatusId: Int,
    val employeeId: Int,
    val insertedDate: String,
    val statusId: Int,
    val content: String
)