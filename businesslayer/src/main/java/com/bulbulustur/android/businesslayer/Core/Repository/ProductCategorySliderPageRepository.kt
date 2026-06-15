package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductCategorySliderPageDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductCategorySliderPageRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductCategorySliderPageUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class ProductCategorySliderPageRepository(
    private val apiClient: ApiClient
) : IProductCategorySliderPageRepository {

    override suspend fun GetProductCategorySliderPageListAsync(): Result<List<ProductCategorySliderPageDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductCategorySliderPageByIdAsync(
        productCategorySliderPageId: Int
    ): Result<ProductCategorySliderPageUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductCategorySliderPageByIdExtendedAsync(
        productCategorySliderPageId: Int
    ): Result<ProductCategorySliderPageDTO?> {
        TODO("Not implemented yet")
    }
}
