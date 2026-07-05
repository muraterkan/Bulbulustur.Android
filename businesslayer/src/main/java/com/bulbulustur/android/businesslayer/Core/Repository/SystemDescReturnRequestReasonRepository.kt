package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescReturnRequestReasonDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescReturnRequestReasonRepository
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescReturnRequestReasonRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescReturnRequestReasonRepository {

    override suspend fun GetSystemDescReturnRequestReasonListAsync(): Result<List<SystemDescReturnRequestReasonDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescReturnRequestReasonListAsync"
        )
    }
}