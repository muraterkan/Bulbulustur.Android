package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.DealsOfTheDayDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IDealsOfTheDayRepository
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class DealsOfTheDayRepository(
    private val apiClient: ApiClient = ApiClient
) : IDealsOfTheDayRepository {

    override suspend fun GetDealsOfTheDaysAsync(languageId: Int, count: Int): Result<List<DealsOfTheDayDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.DEALS_OF_DAY_BASE_URL,
            method = "GetDealsOfTheDaysAsync",
            query = "languageId=$languageId&count=$count"
        )
    }
}
