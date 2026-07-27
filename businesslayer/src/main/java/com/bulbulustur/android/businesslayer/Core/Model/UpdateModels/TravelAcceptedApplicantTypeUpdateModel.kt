package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class TravelAcceptedApplicantTypeUpdateModel(
    val travelAcceptedApplicantTypeId: Int,
    val employeeId: Int,
    val insertedDate: String,
    val statusId: Int,
    val travelId: Int,
    val travelApplicantTypeId: Int
)