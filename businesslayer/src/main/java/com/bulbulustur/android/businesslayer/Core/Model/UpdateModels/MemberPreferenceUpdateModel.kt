package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class MemberPreferenceUpdateModel(
    val MemberPreferenceId: Int = 0,
    val MemberId: Int = 0,
    val PreferenceTypeId: Int = 0,
    val PreferenceValue: Boolean = false
)
