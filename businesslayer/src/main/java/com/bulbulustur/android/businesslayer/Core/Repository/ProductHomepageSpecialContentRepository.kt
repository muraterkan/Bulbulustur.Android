package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.ProductHomepageSpecialContentDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductHomepageSpecialContentRepository
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class ProductHomepageSpecialContentRepository(
    private val apiClient: ApiClient = ApiClient
) : IProductHomepageSpecialContentRepository {

    override suspend fun GetHomepageSpecialContentsAsync(languageId: Int, count: Int): Result<List<ProductHomepageSpecialContentDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.B2C_PRODUCT_BASE_URL,
            method = "GetHomepageSpecialContentsAsync",
            query = "languageId=$languageId&count=$count"
        )
    }
}
