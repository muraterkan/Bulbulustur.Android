package com.bulbulustur.android.businesslayer.Core.DTO

data class PropertyFeatureDTO(
    val propertyFeatureId: Int,
    val propertyId: Int,
    val propertyRoomId: Int?,
    val propertyFeatureTypeId: Int,
    val featureValue: String?,
    val employeeId: Int,
    val insertedDate: String,
    val updatedDate: String?,
    val statusId: Int
)