package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class PropertyHouseholdUpdateModel(
    val propertyHouseholdId: Int,
    val propertyId: Int,
    val householdTypeId: Int?,
    val currentResidentCount: Int,
    val womanCount: Int,
    val manCount: Int,
    val otherGenderCount: Int,
    val coupleCount: Int,
    val childCount: Int,
    val petCount: Int,
    val isSmokingPresent: Boolean,
    val isPetPresent: Boolean,
    val householdDescription: String?,
    val employeeId: Int,
    val insertedDate: String,
    val updatedDate: String?,
    val statusId: Int
)