package com.bulbulustur.android.businesslayer.Core.DTO

data class MemberPreferenceDTO(
    val MemberPreferenceId: Int = 0,
    val MemberId: Int = 0,
    val PreferenceTypeId: Int = 0,
    val PreferenceValue: Boolean = false,
    val PreferenceName: String = "",
    val PreferenceDescription: String? = null
)
