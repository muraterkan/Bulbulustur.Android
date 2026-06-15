package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ReviewDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IReviewRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ReviewUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class ReviewRepository(
    private val apiClient: ApiClient
) : IReviewRepository {

    override suspend fun GetReviewListAsync(): Result<List<ReviewDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetReviewByIdAsync(
        reviewId: Int
    ): Result<ReviewUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetReviewByIdExtendedAsync(
        reviewId: Int
    ): Result<ReviewDTO?> {
        TODO("Not implemented yet")
    }
}
