package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ReviewPictureDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IReviewPictureRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ReviewPictureUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class ReviewPictureRepository(
    private val apiClient: ApiClient
) : IReviewPictureRepository {

    override suspend fun GetReviewPictureListAsync(): Result<List<ReviewPictureDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetReviewPictureByIdAsync(
        reviewPictureId: Int
    ): Result<ReviewPictureUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetReviewPictureByIdExtendedAsync(
        reviewPictureId: Int
    ): Result<ReviewPictureDTO?> {
        TODO("Not implemented yet")
    }
}
