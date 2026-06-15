package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductStatisticDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleProductStatisticUpdateModel

interface IWholesaleProductStatisticRepository {

    suspend fun GetWholesaleProductStatisticListAsync(): Result<List<WholesaleProductStatisticDTO>>

    suspend fun GetWholesaleProductStatisticByIdAsync(
        wholesaleProductStatisticId: Int
    ): Result<WholesaleProductStatisticUpdateModel?>

    suspend fun GetWholesaleProductStatisticByIdExtendedAsync(
        wholesaleProductStatisticId: Int
    ): Result<WholesaleProductStatisticDTO?>
}
