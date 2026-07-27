package com.bulbulustur.android.businesslayer.Core.DTO

data class SystemDescComplaintTypeDTO(
    val systemDescComplaintTypeId: Int,
    val employeeId: Int,
    val insertedDate: String,
    val statusId: Int,
    val content: String
)