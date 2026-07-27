package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class TravelPartyMemberUpdateModel(
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