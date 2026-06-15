package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductCategoryGuideRelatedCategoryDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductCategoryGuideRelatedCategoryRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductCategoryGuideRelatedCategoryUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class ProductCategoryGuideRelatedCategoryRepository(
    private val apiClient: ApiClient
) : IProductCategoryGuideRelatedCategoryRepository {

    override suspend fun GetProductCategoryGuideRelatedCategoryListAsync(): Result<List<ProductCategoryGuideRelatedCategoryDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductCategoryGuideRelatedCategoryByIdAsync(
        productCategoryGuideRelatedCategoryId: Int
    ): Result<ProductCategoryGuideRelatedCategoryUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductCategoryGuideRelatedCategoryByIdExtendedAsync(
        productCategoryGuideRelatedCategoryId: Int
    ): Result<ProductCategoryGuideRelatedCategoryDTO?> {
        TODO("Not implemented yet")
    }
}
