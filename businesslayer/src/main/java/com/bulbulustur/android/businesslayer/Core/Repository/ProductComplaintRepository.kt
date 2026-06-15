package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductComplaintDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductComplaintRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductComplaintUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class ProductComplaintRepository(
    private val apiClient: ApiClient
) : IProductComplaintRepository {

    override suspend fun GetProductComplaintListAsync(): Result<List<ProductComplaintDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductComplaintByIdAsync(
        complaintId: Int
    ): Result<ProductComplaintUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductComplaintByIdExtendedAsync(
        complaintId: Int
    ): Result<ProductComplaintDTO?> {
        TODO("Not implemented yet")
    }
}
