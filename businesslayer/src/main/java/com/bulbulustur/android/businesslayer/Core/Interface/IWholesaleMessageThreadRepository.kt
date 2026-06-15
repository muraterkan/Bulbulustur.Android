package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleMessageThreadDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleMessageThreadUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IWholesaleMessageThreadRepository {

    suspend fun GetWholesaleMessageThreadListAsync(): Result<List<WholesaleMessageThreadDTO>>

    suspend fun GetWholesaleMessageThreadByIdAsync(
        wholesaleMessageThreadId: Int
    ): Result<WholesaleMessageThreadUpdateModel?>

    suspend fun GetWholesaleMessageThreadByIdExtendedAsync(
        wholesaleMessageThreadId: Int
    ): Result<WholesaleMessageThreadDTO?>
}