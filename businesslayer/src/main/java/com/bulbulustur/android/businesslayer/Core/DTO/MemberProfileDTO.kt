package com.bulbulustur.android.businesslayer.Core.DTO

data class MemberProfileDTO(
    val MemberProfileId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val Bio: String? = null,
    val Profession: String? = null,
    val JobTitle: String? = null,
    val EducationId: Int? = null,
    val ProfileScore: Int = 0,
    val IsProfileComplete: Boolean = false
)
