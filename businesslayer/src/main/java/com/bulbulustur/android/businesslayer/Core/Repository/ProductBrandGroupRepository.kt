package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductBrandGroupDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductBrandGroupRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductBrandGroupUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class ProductBrandGroupRepository(
    private val apiClient: ApiClient
) : IProductBrandGroupRepository {

    override suspend fun GetProductBrandGroupListAsync(): Result<List<ProductBrandGroupDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductBrandGroupByIdAsync(
        brandGroupId: Int
    ): Result<ProductBrandGroupUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductBrandGroupByIdExtendedAsync(
        brandGroupId: Int
    ): Result<ProductBrandGroupDTO?> {
        TODO("Not implemented yet")
    }
}
