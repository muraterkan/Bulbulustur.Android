package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.ReviewDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IReviewRepository
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class ReviewRepository(
    private val apiClient: ApiClient = ApiClient
) : IReviewRepository {

    override suspend fun GetReviewsAsync(sourceType: String, sourceId: Int, variantId: Int, page: Int, pageSize: Int): Result<List<ReviewDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_BASE_URL,
            method = "Review/GetReviewsAsync",
            query = "sourceType=$sourceType&sourceId=$sourceId&variantId=$variantId&page=$page&pageSize=$pageSize"
        )
    }

    override suspend fun GetMemberReviewsAsync(memberId: Int, count: Int): Result<List<ReviewDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_BASE_URL,
            method = "Review/GetMemberReviewsAsync",
            query = "memberId=$memberId&count=$count"
        )
    }
}