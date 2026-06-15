package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductPropertyValueDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductPropertyValueRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductPropertyValueUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class ProductPropertyValueRepository(
    private val apiClient: ApiClient
) : IProductPropertyValueRepository {

    override suspend fun GetProductPropertyValueListAsync(): Result<List<ProductPropertyValueDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductPropertyValueByIdAsync(
        propertyValueId: Int
    ): Result<ProductPropertyValueUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductPropertyValueByIdExtendedAsync(
        propertyValueId: Int
    ): Result<ProductPropertyValueDTO?> {
        TODO("Not implemented yet")
    }
}
