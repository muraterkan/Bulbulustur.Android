package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleHomepageSpecialContentDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleHomepageSpecialContentRepository
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class WholesaleHomepageSpecialContentRepository(
    private val apiClient: ApiClient = ApiClient
) : IWholesaleHomepageSpecialContentRepository {

    override suspend fun GetHomepageSpecialContents(languageId: Int, count: Int): Result<List<WholesaleHomepageSpecialContentDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.B2B_PRODUCT_BASE_URL,
            method = "GetHomepageSpecialContents",
            query = "languageId=$languageId&count=$count"
        )
    }
}