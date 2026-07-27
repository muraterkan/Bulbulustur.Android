package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class TravelApplicationUpdateModel(
    val travelApplicationId: Int,
    val employeeId: Int,
    val insertedDate: String,
    val updatedDate: String?,
    val statusId: Int,
    val travelId: Int,
    val applicantMemberId: Int,
    val travelApplicationTypeId: Int,
    val travelApplicationStatusId: Int,
    val message: String,
    val viewedDate: String?,
    val respondedDate: String?,
    val withdrawnDate: String?,
    val expiresDate: String?
)