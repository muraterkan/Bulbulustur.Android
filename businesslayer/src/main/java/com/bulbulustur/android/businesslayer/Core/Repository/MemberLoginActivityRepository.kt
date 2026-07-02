package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.MemberLoginActivityDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberLoginActivityRepository
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class MemberLoginActivityRepository(private val apiClient: ApiClient = ApiClient) : IMemberLoginActivityRepository {

    override suspend fun GetAccountLoginActivities(memberId: Int, count: Int): Result<List<MemberLoginActivityDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_ACCOUNT_BASE_URL,
            method = "GetAccountLoginActivities",
            query = "memberId=$memberId&count=$count"
        )
    }
}