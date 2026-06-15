package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductSizeTypeByCategoryDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductSizeTypeByCategoryRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductSizeTypeByCategoryUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class ProductSizeTypeByCategoryRepository(
    private val apiClient: ApiClient
) : IProductSizeTypeByCategoryRepository {

    override suspend fun GetProductSizeTypeByCategoryListAsync(): Result<List<ProductSizeTypeByCategoryDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductSizeTypeByCategoryByIdAsync(
        productSizeTypeByCategoryId: Int
    ): Result<ProductSizeTypeByCategoryUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductSizeTypeByCategoryByIdExtendedAsync(
        productSizeTypeByCategoryId: Int
    ): Result<ProductSizeTypeByCategoryDTO?> {
        TODO("Not implemented yet")
    }
}
