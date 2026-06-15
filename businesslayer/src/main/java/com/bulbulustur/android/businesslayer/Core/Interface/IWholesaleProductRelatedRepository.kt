package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductRelatedDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleProductRelatedUpdateModel

interface IWholesaleProductRelatedRepository {

    suspend fun GetWholesaleProductRelatedListAsync(): Result<List<WholesaleProductRelatedDTO>>

    suspend fun GetWholesaleProductRelatedByIdAsync(
        wholesaleProductRelatedId: Int
    ): Result<WholesaleProductRelatedUpdateModel?>

    suspend fun GetWholesaleProductRelatedByIdExtendedAsync(
        wholesaleProductRelatedId: Int
    ): Result<WholesaleProductRelatedDTO?>
}
