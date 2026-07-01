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

    override suspend fun GetReviewsAsync(
        sourceType: String,
        sourceId: Int,
        variantId: Int,
        page: Int,
        pageSize: Int
    ): Result<List<ReviewDTO>> {
        return apiClient.GetAsync(
            baseUrl =
                ApiRoutes.COMMERCE_SUPPORT_REVIEW_BASE_URL,
            method =
                "GetReviewsAsync",
            query =
                "sourceType=$sourceType" +
                        "&sourceId=$sourceId" +
                        "&variantId=$variantId" +
                        "&page=$page" +
                        "&pageSize=$pageSize"
        )
    }

    override suspend fun GetMemberReviewsAsync(
        memberId: Int,
        count: Int
    ): Result<List<ReviewDTO>> {
        return apiClient.GetAsync(
            baseUrl =
                ApiRoutes.COMMERCE_SUPPORT_REVIEW_BASE_URL,
            method =
                "GetMemberReviewsAsync",
            query =
                "memberId=$memberId" +
                        "&count=$count"
        )
    }

    override suspend fun GetReviewByIdExtendedAsync(
        languageId: Int,
        reviewId: Int
    ): Result<ReviewDTO?> {
        return apiClient.GetAsync(
            baseUrl =
                ApiRoutes.COMMERCE_SUPPORT_REVIEW_BASE_URL,
            method =
                "GetReviewByIdExtendedAsync",
            query =
                "languageId=$languageId" +
                        "&reviewId=$reviewId"
        )
    }

    override suspend fun Insert(
        model: ReviewInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl =
                ApiRoutes.COMMERCE_SUPPORT_REVIEW_BASE_URL,
            method =
                "Insert",
            data =
                model
        )
    }

    override suspend fun Update(
        model: ReviewUpdateModel
    ): Result<Unit> {
        return apiClient.PutAsync(
            baseUrl =
                ApiRoutes.COMMERCE_SUPPORT_REVIEW_BASE_URL,
            method =
                "Update",
            data =
                model
        )
    }
}