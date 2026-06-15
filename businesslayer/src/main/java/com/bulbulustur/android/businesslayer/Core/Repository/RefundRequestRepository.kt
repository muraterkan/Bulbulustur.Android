package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.RefundRequestDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IRefundRequestRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.RefundRequestUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class RefundRequestRepository(
    private val apiClient: ApiClient
) : IRefundRequestRepository {

    override suspend fun GetRefundRequestListAsync(): Result<List<RefundRequestDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetRefundRequestByIdAsync(
        refundRequestId: Int
    ): Result<RefundRequestUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetRefundRequestByIdExtendedAsync(
        refundRequestId: Int
    ): Result<RefundRequestDTO?> {
        TODO("Not implemented yet")
    }
}
