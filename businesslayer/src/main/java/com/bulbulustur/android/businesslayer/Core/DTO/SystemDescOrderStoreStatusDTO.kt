package com.bulbulustur.android.businesslayer.Core.DTO

data class SystemDescOrderStoreStatusDTO(
    val systemDescOrderStoreStatusId: Int,
    val employeeId: Int,
    val insertedDate: String,
    val statusId: Int,
    val content: String
)