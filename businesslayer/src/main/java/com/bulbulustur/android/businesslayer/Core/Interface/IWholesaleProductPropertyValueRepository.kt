package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductPropertyValueDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleProductPropertyValueUpdateModel

interface IWholesaleProductPropertyValueRepository {

    suspend fun GetWholesaleProductPropertyValueListAsync(): Result<List<WholesaleProductPropertyValueDTO>>

    suspend fun GetWholesaleProductPropertyValueByIdAsync(
        wholesaleProductPropertyValueId: Int
    ): Result<WholesaleProductPropertyValueUpdateModel?>

    suspend fun GetWholesaleProductPropertyValueByIdExtendedAsync(
        wholesaleProductPropertyValueId: Int
    ): Result<WholesaleProductPropertyValueDTO?>
}
