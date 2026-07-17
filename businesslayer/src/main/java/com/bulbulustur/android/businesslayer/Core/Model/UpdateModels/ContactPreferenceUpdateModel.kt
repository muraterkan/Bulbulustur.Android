package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class ContactPreferenceUpdateModel(
    val MemberId: Int = 0,
    val ContactPreferenceSms: Int = 0,
    val ContactPreferenceEmail: Int = 0,
    val ContactPreferencePhone: Int = 0
)