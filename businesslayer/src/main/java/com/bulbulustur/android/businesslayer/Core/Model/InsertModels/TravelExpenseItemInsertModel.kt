package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class TravelExpenseItemInsertModel(
    val employeeId: Int,
    val insertedDate: String,
    val updatedDate: String?,
    val statusId: Int,
    val travelId: Int,
    val travelExpenseItemTypeId: Int,
    val responsibilityCode: String,
    val description: String
)