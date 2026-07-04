package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.StoreRequestDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IStoreRequestRepository
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class StoreRequestRepository(private val apiClient: ApiClient = ApiClient) : IStoreRequestRepository {

    override suspend fun GetAccountStoreRequestStatusAsync(memberId: Int): Result<StoreRequestDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_BASE_URL,
            method = "Company/GetAccountStoreRequestStatusAsync",
            query = "memberId=$memberId"
        )
    }
}