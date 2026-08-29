package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.DealsOfTheDayDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IDealsOfTheDayRepository
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.PaginatedList
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

    override suspend fun GetDealsOfTheDaysByProductCategoryListAsync(languageId: Int, productCategoryIds: List<Int>, count: Int): Result<List<DealsOfTheDayDTO>> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.B2C_BASE_URL,
            method = "DealsOfTheDay/GetDealsOfTheDaysByProductCategoryListAsync?languageId=$languageId&count=$count",
            data = productCategoryIds
        )
    }

    override suspend fun GetDealsOfTheDaysByProductCategoryListPagedAsync(languageId: Int, productCategoryIds: List<Int>, page: Int, pageSize: Int): Result<PaginatedList<DealsOfTheDayDTO>> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.B2C_BASE_URL,
            method = "DealsOfTheDay/GetDealsOfTheDaysByProductCategoryListPagedAsync?languageId=$languageId&page=$page&pageSize=$pageSize",
            data = productCategoryIds
        )
    }
}
