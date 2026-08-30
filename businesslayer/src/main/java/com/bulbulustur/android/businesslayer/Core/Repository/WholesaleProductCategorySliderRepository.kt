package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductCategorySliderDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleProductCategorySliderRepository
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class WholesaleProductCategorySliderRepository(
    private val apiClient: ApiClient = ApiClient
) : IWholesaleProductCategorySliderRepository {

    override suspend fun GetWholesaleProductCategorySlider(languageId: Int, productCategoryId: Int): Result<WholesaleProductCategorySliderDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.B2B_PRODUCT_CATEGORY_SLIDER_BASE_URL,
            method = "GetWholesaleProductCategorySlider",
            query = "languageId=$languageId&productCategoryId=$productCategoryId"
        )
    }
}
