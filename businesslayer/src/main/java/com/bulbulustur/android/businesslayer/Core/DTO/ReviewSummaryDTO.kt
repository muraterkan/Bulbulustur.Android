package com.bulbulustur.android.businesslayer.Core.DTO

data class ReviewSummaryDTO(
    val ReviewNumber: Double = 0.0,
    val AverageRating: Double = 0.0,
    val RatingGroups: List<RatingGroupDTO> = emptyList()
)

data class RatingGroupDTO(
    val StarNumber: Int = 0,
    val ReviewNumber: Int = 0
)