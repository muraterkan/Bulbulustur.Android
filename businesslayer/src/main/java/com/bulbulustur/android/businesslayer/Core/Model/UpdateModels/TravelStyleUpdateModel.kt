package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class TravelStyleUpdateModel(
    val travelStyleId: Int,
    val tripStyleId: Int,
    val employeeId: Int,
    val insertedDate: String,
    val statusId: Int,
    val tripId: Int
)