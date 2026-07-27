package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class SystemDescVisibilityTypeUpdateModel(
    val systemDescVisibilityTypeId: Int,
    val employeeId: Int,
    val insertedDate: String,
    val statusId: Int,
    val code: String,
    val content: String,
    val displayOrder: Int
)