package com.bulbulustur.android.businesslayer.Core.DTO

data class TravelPartyMemberDTO(
    val travelPartyMemberId: Int,
    val employeeId: Int,
    val insertedDate: String,
    val updatedDate: String?,
    val statusId: Int,
    val travelId: Int,
    val memberId: Int,
    val roleCode: String,
    val isConfirmed: Boolean,
    val confirmedDate: String?
)