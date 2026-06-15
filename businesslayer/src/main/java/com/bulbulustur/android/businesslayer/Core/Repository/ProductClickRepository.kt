package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductClickDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductClickRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductClickUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class ProductClickRepository(
    private val apiClient: ApiClient
) : IProductClickRepository {

    override suspend fun GetProductClickListAsync(): Result<List<ProductClickDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductClickByIdAsync(
        productClickId: Int
    ): Result<ProductClickUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductClickByIdExtendedAsync(
        productClickId: Int
    ): Result<ProductClickDTO?> {
        TODO("Not implemented yet")
    }
}
