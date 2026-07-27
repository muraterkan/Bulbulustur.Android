package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class SystemDescRoomTypeUpdateModel(
    val systemDescRoomTypeId: Int,
    val employeeId: Int,
    val insertedDate: String,
    val statusId: Int,
    val code: String,
    val content: String,
    val displayOrder: Int
)