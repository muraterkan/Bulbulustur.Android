package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductCategorySliderDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductCategorySliderRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductCategorySliderUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class ProductCategorySliderRepository(
    private val apiClient: ApiClient
) : IProductCategorySliderRepository {

    override suspend fun GetProductCategorySliderListAsync(): Result<List<ProductCategorySliderDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductCategorySliderByIdAsync(
        productCategorySliderId: Int
    ): Result<ProductCategorySliderUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductCategorySliderByIdExtendedAsync(
        productCategorySliderId: Int
    ): Result<ProductCategorySliderDTO?> {
        TODO("Not implemented yet")
    }
}
