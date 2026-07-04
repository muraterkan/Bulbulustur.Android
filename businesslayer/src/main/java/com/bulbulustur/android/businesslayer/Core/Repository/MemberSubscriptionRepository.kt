package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.MemberSubscriptionDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberSubscriptionRepository
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class MemberSubscriptionRepository(
    private val apiClient: ApiClient = ApiClient
) : IMemberSubscriptionRepository {

    override suspend fun GetAccountSubscriptionsAsync(memberId: Int, count: Int): Result<List<MemberSubscriptionDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_BASE_URL,
            method = "Account/GetAccountSubscriptionsAsync",
            query = "memberId=$memberId&count=$count"
        )
    }

    override suspend fun GetAccountSubscriptionByIdExtendedAsync(memberId: Int, memberSubscriptionId: Int): Result<MemberSubscriptionDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_BASE_URL,
            method = "Account/GetAccountSubscriptionByIdExtendedAsync",
            query = "memberId=$memberId&memberSubscriptionId=$memberSubscriptionId"
        )
    }
}