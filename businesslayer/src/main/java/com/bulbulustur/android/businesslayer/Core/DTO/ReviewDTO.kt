package com.bulbulustur.android.businesslayer.Core.DTO

data class ReviewDTO(
    val ReviewId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val SourceType: String = "",
    val ItemId: Int = 0,
    val SecureKey: String? = null,
    val VariantId: Int? = null,
    val MemberId: Int = 0,
    val Content: String = "",
    val Rating: Double = 0.0,
    val ConfirmationStatusId: Int = 0,
    val ProductName: String = "",
    val Picture: String? = null,
    val Name: String = "",
    val Surname: String = "",
    val Fullname: String? = null,
    val ReviewStatus: String? = null,
    val CountryName: String = "",
    val CityName: String = "",
    val DefaultPicture: String? = null,
    val ReviewPictures: List<ReviewPictureDTO> = emptyList()
)