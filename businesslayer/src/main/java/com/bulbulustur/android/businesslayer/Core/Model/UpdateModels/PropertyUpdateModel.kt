package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class PropertyUpdateModel(
    val propertyId: Int,
    val memberId: Int,
    val propertyTypeId: Int,
    val countryId: Int,
    val stateId: Int?,
    val departmentId: Int?,
    val cityId: Int,
    val districtId: Int?,
    val neighborhoodId: Int?,
    val addressLine: String?,
    val postalCode: String?,
    val latitude: Double?,
    val longitude: Double?,
    val totalRoomCount: Int?,
    val bathroomCount: Int?,
    val floorNumber: Int?,
    val buildingFloorCount: Int?,
    val buildingAge: Int?,
    val propertyDescription: String?,
    val employeeId: Int,
    val insertedDate: String,
    val updatedDate: String?,
    val statusId: Int
)