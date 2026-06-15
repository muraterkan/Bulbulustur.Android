package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductCategoryGuideDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductCategoryGuideRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductCategoryGuideUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class ProductCategoryGuideRepository(
    private val apiClient: ApiClient
) : IProductCategoryGuideRepository {

    override suspend fun GetProductCategoryGuideListAsync(): Result<List<ProductCategoryGuideDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductCategoryGuideByIdAsync(
        productCategoryGuideId: Int
    ): Result<ProductCategoryGuideUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductCategoryGuideByIdExtendedAsync(
        productCategoryGuideId: Int
    ): Result<ProductCategoryGuideDTO?> {
        TODO("Not implemented yet")
    }
}
