package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.ReviewDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IReviewRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ReviewInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ReviewUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class ReviewRepository(
    private val apiClient: ApiClient = ApiClient
) : IReviewRepository {

    override suspend fun GetReviewListAsync(): Result<List<ReviewDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetReviewListAsync"
        )
    }

    override suspend fun GetReviewByIdAsync(
        reviewId: Int
    ): Result<ReviewUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetReviewByIdAsync",
            query = "reviewId=$reviewId"
        )
    }

    override suspend fun GetReviewByIdExtendedAsync(
        reviewId: Int
    ): Result<ReviewDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetReviewByIdExtendedAsync",
            query = "reviewId=$reviewId"
        )
    }

    override suspend fun InsertAsync(
        model: ReviewInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: ReviewUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        reviewId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "reviewId=$reviewId"
        )
    }
}