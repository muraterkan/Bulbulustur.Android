package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleHomepageFeaturedProductDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleHomepageFeaturedProductRepository
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class WholesaleHomepageFeaturedProductRepository(
    private val apiClient: ApiClient = ApiClient
) : IWholesaleHomepageFeaturedProductRepository {

    override suspend fun GetHomepageFeaturedProductsAsync(count: Int): Result<List<WholesaleHomepageFeaturedProductDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.B2B_PRODUCT_BASE_URL,
            method = "GetHomepageFeaturedProductsAsync",
            query = "count=$count"
        )
    }
}