package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.StatusOverviewDTO
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IStatusRepository {

    suspend fun GetOverviewAsync(): Result<StatusOverviewDTO?>
}