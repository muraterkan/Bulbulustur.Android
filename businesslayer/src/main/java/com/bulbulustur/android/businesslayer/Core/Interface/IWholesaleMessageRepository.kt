package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleMessageDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleMessageUpdateModel

interface IWholesaleMessageRepository {

    suspend fun GetWholesaleMessageListAsync(): Result<List<WholesaleMessageDTO>>

    suspend fun GetWholesaleMessageIdByIdAsync(
        wholesaleMessageId: Int
    ): Result<WholesaleMessageUpdateModel?>

    suspend fun GetWholesaleMessageIdByIdExtendedAsync(
        wholesaleMessageId: Int
    ): Result<WholesaleMessageDTO?>
}
