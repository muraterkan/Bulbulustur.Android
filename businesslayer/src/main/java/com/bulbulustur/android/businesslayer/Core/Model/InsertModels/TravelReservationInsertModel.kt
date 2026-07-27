package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class TravelReservationInsertModel(
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