package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductCategoryDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductCategoryRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductCategoryUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class ProductCategoryRepository(
    private val apiClient: ApiClient
) : IProductCategoryRepository {

    override suspend fun GetProductCategoryListAsync(): Result<List<ProductCategoryDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductCategoryByIdAsync(
        productCategoryId: Int
    ): Result<ProductCategoryUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductCategoryByIdExtendedAsync(
        productCategoryId: Int
    ): Result<ProductCategoryDTO?> {
        TODO("Not implemented yet")
    }
}
