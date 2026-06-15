package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductPriceDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleProductPriceUpdateModel

interface IWholesaleProductPriceRepository {

    suspend fun GetWholesaleProductPriceListAsync(): Result<List<WholesaleProductPriceDTO>>

    suspend fun GetWholesaleProductPriceByIdAsync(
        productPriceId: Int
    ): Result<WholesaleProductPriceUpdateModel?>

    suspend fun GetWholesaleProductPriceByIdExtendedAsync(
        productPriceId: Int
    ): Result<WholesaleProductPriceDTO?>
}
