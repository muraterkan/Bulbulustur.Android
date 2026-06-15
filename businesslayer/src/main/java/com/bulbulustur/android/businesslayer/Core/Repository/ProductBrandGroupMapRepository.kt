package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductBrandGroupMapDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductBrandGroupMapRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductBrandGroupMapUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class ProductBrandGroupMapRepository(
    private val apiClient: ApiClient
) : IProductBrandGroupMapRepository {

    override suspend fun GetProductBrandGroupMapListAsync(): Result<List<ProductBrandGroupMapDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductBrandGroupMapByIdAsync(
        brandGroupMapId: Int
    ): Result<ProductBrandGroupMapUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductBrandGroupMapByIdExtendedAsync(
        brandGroupMapId: Int
    ): Result<ProductBrandGroupMapDTO?> {
        TODO("Not implemented yet")
    }
}
