package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class PropertyFeatureInsertModel(
    val propertyId: Int,
    val propertyRoomId: Int?,
    val propertyFeatureTypeId: Int,
    val featureValue: String?,
    val employeeId: Int,
    val insertedDate: String,
    val updatedDate: String?,
    val statusId: Int
)