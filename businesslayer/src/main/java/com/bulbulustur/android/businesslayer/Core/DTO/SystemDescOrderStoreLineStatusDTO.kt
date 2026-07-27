package com.bulbulustur.android.businesslayer.Core.DTO

data class SystemDescOrderStoreLineStatusDTO(
    val systemDescOrderStoreLineStatusId: Int,
    val employeeId: Int,
    val insertedDate: String,
    val statusId: Int,
    val content: String
)