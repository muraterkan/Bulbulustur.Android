package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ReturnRequestDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IReturnRequestRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ReturnRequestUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class ReturnRequestRepository(
    private val apiClient: ApiClient
) : IReturnRequestRepository {

    override suspend fun GetReturnRequestListAsync(): Result<List<ReturnRequestDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetReturnRequestByIdAsync(
        returnRequestId: Int
    ): Result<ReturnRequestUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetReturnRequestByIdExtendedAsync(
        returnRequestId: Int
    ): Result<ReturnRequestDTO?> {
        TODO("Not implemented yet")
    }
}
