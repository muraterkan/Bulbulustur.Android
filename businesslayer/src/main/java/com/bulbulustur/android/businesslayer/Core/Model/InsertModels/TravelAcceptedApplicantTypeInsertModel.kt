package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class TravelAcceptedApplicantTypeInsertModel(
    val employeeId: Int,
    val insertedDate: String,
    val statusId: Int,
    val travelId: Int,
    val travelApplicantTypeId: Int
)