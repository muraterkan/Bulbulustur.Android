package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescReturnRequestReasonDTO
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescReturnRequestReasonRepository {

    suspend fun GetSystemDescReturnRequestReasonListAsync(): Result<List<SystemDescReturnRequestReasonDTO>>
}