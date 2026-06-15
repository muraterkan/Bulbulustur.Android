package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductPropertyDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductPropertyRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductPropertyUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class ProductPropertyRepository(
    private val apiClient: ApiClient
) : IProductPropertyRepository {

    override suspend fun GetProductPropertyListAsync(): Result<List<ProductPropertyDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductPropertyByIdAsync(
        propertyId: Int
    ): Result<ProductPropertyUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductPropertyByIdExtendedAsync(
        propertyId: Int
    ): Result<ProductPropertyDTO?> {
        TODO("Not implemented yet")
    }
}
