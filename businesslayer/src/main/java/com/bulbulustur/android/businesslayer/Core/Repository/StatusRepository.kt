package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.StatusOverviewDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IStatusRepository
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class StatusRepository(
    private val apiClient: ApiClient = ApiClient
) : IStatusRepository {

    override suspend fun GetOverviewAsync(): Result<StatusOverviewDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.STATUS_BASE_URL,
            method = "overview"
        )
    }
}