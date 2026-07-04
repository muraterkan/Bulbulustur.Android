package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.ReturnRequestDTO
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IReturnRequestRepository {

    suspend fun GetReturnRequestsAsync(languageId: Int, memberId: Int, count: Int = 100): Result<List<ReturnRequestDTO>>

    suspend fun GetReturnRequestByIdExtendedAsync(languageId: Int, memberId: Int, returnRequestId: Int): Result<ReturnRequestDTO?>

    suspend fun GetReturnRequestSimpleAsync(memberId: Int, returnRequestId: Int): Result<ReturnRequestDTO?>
}