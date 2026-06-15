package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ReviewDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ReviewUpdateModel

interface IReviewRepository {

    suspend fun GetReviewListAsync(): Result<List<ReviewDTO>>

    suspend fun GetReviewByIdAsync(
        reviewId: Int
    ): Result<ReviewUpdateModel?>

    suspend fun GetReviewByIdExtendedAsync(
        reviewId: Int
    ): Result<ReviewDTO?>
}
