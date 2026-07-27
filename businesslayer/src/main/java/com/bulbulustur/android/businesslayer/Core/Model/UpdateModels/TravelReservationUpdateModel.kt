package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class TravelReservationUpdateModel(
    val travelReservationId: Int,
    val employeeId: Int,
    val insertedDate: String,
    val updatedDate: String?,
    val statusId: Int,
    val travelId: Int,
    val travelReservationTypeId: Int,
    val travelReservationStatusId: Int,
    val reservationDate: String?,
    val description: String
)