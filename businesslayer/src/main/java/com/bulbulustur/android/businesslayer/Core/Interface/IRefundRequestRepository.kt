package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.RefundRequestDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.RefundRequestUpdateModel

interface IRefundRequestRepository {

    suspend fun GetRefundRequestListAsync(): Result<List<RefundRequestDTO>>

    suspend fun GetRefundRequestByIdAsync(
        refundRequestId: Int
    ): Result<RefundRequestUpdateModel?>

    suspend fun GetRefundRequestByIdExtendedAsync(
        refundRequestId: Int
    ): Result<RefundRequestDTO?>
}
