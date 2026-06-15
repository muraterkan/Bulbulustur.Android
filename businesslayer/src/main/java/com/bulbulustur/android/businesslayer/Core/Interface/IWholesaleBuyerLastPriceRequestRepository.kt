package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleBuyerLastPriceRequestDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleBuyerLastPriceRequestUpdateModel

interface IWholesaleBuyerLastPriceRequestRepository {

    suspend fun GetWholesaleBuyerLastPriceRequestListAsync(): Result<List<WholesaleBuyerLastPriceRequestDTO>>

    suspend fun GetWholesaleBuyerLastPriceRequestByIdAsync(
        wholesaleBuyerLastPriceRequestId: Int
    ): Result<WholesaleBuyerLastPriceRequestUpdateModel?>

    suspend fun GetWholesaleBuyerLastPriceRequestByIdExtendedAsync(
        wholesaleBuyerLastPriceRequestId: Int
    ): Result<WholesaleBuyerLastPriceRequestDTO?>
}
