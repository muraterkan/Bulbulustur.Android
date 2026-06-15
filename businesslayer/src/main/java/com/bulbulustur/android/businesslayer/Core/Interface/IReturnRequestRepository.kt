package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ReturnRequestDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ReturnRequestUpdateModel

interface IReturnRequestRepository {

    suspend fun GetReturnRequestListAsync(): Result<List<ReturnRequestDTO>>

    suspend fun GetReturnRequestByIdAsync(
        returnRequestId: Int
    ): Result<ReturnRequestUpdateModel?>

    suspend fun GetReturnRequestByIdExtendedAsync(
        returnRequestId: Int
    ): Result<ReturnRequestDTO?>
}
