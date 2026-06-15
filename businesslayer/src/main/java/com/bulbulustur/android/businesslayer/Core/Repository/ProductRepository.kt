package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class ProductRepository(
    private val apiClient: ApiClient
) : IProductRepository {

    override suspend fun GetProductListAsync(): Result<List<ProductDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductByIdAsync(
        productId: Int
    ): Result<ProductUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductByIdExtendedAsync(
        productId: Int
    ): Result<ProductDTO?> {
        TODO("Not implemented yet")
    }
}
