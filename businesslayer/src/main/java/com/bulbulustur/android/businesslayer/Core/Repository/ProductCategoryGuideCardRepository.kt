package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductCategoryGuideCardDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductCategoryGuideCardRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductCategoryGuideCardUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class ProductCategoryGuideCardRepository(
    private val apiClient: ApiClient
) : IProductCategoryGuideCardRepository {

    override suspend fun GetProductCategoryGuideCardListAsync(): Result<List<ProductCategoryGuideCardDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductCategoryGuideCardByIdAsync(
        productCategoryGuideCardId: Int
    ): Result<ProductCategoryGuideCardUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductCategoryGuideCardByIdExtendedAsync(
        productCategoryGuideCardId: Int
    ): Result<ProductCategoryGuideCardDTO?> {
        TODO("Not implemented yet")
    }
}
