package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.StoreRequestDTO
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IStoreRequestRepository {
    suspend fun GetAccountStoreRequestStatusAsync(memberId: Int): Result<StoreRequestDTO?>
}