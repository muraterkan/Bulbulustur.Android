package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.ReviewPictureDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IReviewPictureRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ReviewPictureInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ReviewPictureUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class ReviewPictureRepository(
    private val apiClient: ApiClient = ApiClient
) : IReviewPictureRepository {

    override suspend fun GetReviewPictureListAsync(): Result<List<ReviewPictureDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetReviewPictureListAsync"
        )
    }

    override suspend fun GetReviewPictureByIdAsync(
        reviewPictureId: Int
    ): Result<ReviewPictureUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetReviewPictureByIdAsync",
            query = "reviewPictureId=$reviewPictureId"
        )
    }

    override suspend fun GetReviewPictureByIdExtendedAsync(
        reviewPictureId: Int
    ): Result<ReviewPictureDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetReviewPictureByIdExtendedAsync",
            query = "reviewPictureId=$reviewPictureId"
        )
    }

    override suspend fun InsertAsync(
        model: ReviewPictureInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: ReviewPictureUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        reviewPictureId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "reviewPictureId=$reviewPictureId"
        )
    }
}