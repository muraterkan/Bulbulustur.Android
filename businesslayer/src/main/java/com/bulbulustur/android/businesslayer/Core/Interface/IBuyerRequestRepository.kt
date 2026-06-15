package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.BuyerRequestDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.BuyerRequestUpdateModel

interface IBuyerRequestRepository {

    suspend fun GetBuyerRequestListAsync(): Result<List<BuyerRequestDTO>>

    suspend fun GetBuyerRequestByIdAsync(
        buyerRequestId: Int
    ): Result<BuyerRequestUpdateModel?>

    suspend fun GetBuyerRequestByIdExtendedAsync(
        buyerRequestId: Int
    ): Result<BuyerRequestDTO?>
}
