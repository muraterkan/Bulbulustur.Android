package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductFavoriteDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductFavoriteRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductFavoriteUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class ProductFavoriteRepository(
    private val apiClient: ApiClient
) : IProductFavoriteRepository {

    override suspend fun GetProductFavoriteListAsync(): Result<List<ProductFavoriteDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductFavoriteByIdAsync(
        productFavoriteId: Int
    ): Result<ProductFavoriteUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductFavoriteByIdExtendedAsync(
        productFavoriteId: Int
    ): Result<ProductFavoriteDTO?> {
        TODO("Not implemented yet")
    }
}
