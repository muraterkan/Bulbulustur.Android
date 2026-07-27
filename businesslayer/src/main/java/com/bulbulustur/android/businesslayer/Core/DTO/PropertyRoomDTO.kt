package com.bulbulustur.android.businesslayer.Core.DTO

data class PropertyRoomDTO(
    val propertyRoomId: Int,
    val propertyId: Int,
    val roomTypeId: Int,
    val roomName: String?,
    val roomDescription: String?,
    val roomSizeSquareMeter: Double?,
    val monthlyRent: Double,
    val depositAmount: Double?,
    val currencyId: Int,
    val availableFrom: String?,
    val minimumStayMonth: Int?,
    val maximumOccupantCount: Int,
    val isFurnished: Boolean,
    val hasPrivateBathroom: Boolean,
    val hasBalcony: Boolean,
    val isAvailable: Boolean,
    val childrenHouseholdPreferenceId: Int?,
    val petHouseholdPreferenceId: Int?,
    val coupleAcceptanceTypeId: Int?,
    val isSmokingAllowed: Boolean,
    val employeeId: Int,
    val insertedDate: String,
    val updatedDate: String?,
    val statusId: Int
)