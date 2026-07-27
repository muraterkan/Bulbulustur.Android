package com.bulbulustur.android.businesslayer.Core.DTO

data class AccommodationRequestLocationDTO(
    val accommodationRequestLocationId: Int,
    val accommodationRequestId: Int,
    val countryId: Int,
    val stateId: Int?,
    val departmentId: Int?,
    val cityId: Int,
    val districtId: Int?,
    val neighborhoodId: Int?,
    val displayOrder: Int,
    val employeeId: Int,
    val insertedDate: String,
    val updatedDate: String?,
    val statusId: Int
)