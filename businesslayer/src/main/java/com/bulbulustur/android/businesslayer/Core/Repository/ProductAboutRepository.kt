package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductAboutDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductAboutRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductAboutUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class ProductAboutRepository(
    private val apiClient: ApiClient
) : IProductAboutRepository {

    override suspend fun GetProductAboutListAsync(): Result<List<ProductAboutDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductAboutByIdAsync(
        productAboutId: Int
    ): Result<ProductAboutUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductAboutByIdExtendedAsync(
        productAboutId: Int
    ): Result<ProductAboutDTO?> {
        TODO("Not implemented yet")
    }
}
