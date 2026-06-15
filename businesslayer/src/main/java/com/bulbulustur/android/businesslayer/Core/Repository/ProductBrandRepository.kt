package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductBrandDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductBrandRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductBrandUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class ProductBrandRepository(
    private val apiClient: ApiClient
) : IProductBrandRepository {

    override suspend fun GetProductBrandListAsync(): Result<List<ProductBrandDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductBrandByIdAsync(
        brandId: Int
    ): Result<ProductBrandUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductBrandByIdExtendedAsync(
        brandId: Int
    ): Result<ProductBrandDTO?> {
        TODO("Not implemented yet")
    }
}
