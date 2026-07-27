package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class SystemDescVisibilityTypeInsertModel(
    val employeeId: Int,
    val insertedDate: String,
    val statusId: Int,
    val code: String,
    val content: String,
    val displayOrder: Int
)