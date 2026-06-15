package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleBuyerCustomizeRequestDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleBuyerCustomizeRequestUpdateModel

interface IWholesaleBuyerCustomizeRequestRepository {

    suspend fun GetWholesaleBuyerCustomizeRequestListAsync(): Result<List<WholesaleBuyerCustomizeRequestDTO>>

    suspend fun GetWholesaleBuyerCustomizeRequestByIdAsync(
        wholesaleBuyerCustomizeRequestId: Int
    ): Result<WholesaleBuyerCustomizeRequestUpdateModel?>

    suspend fun GetWholesaleBuyerCustomizeRequestByIdExtendedAsync(
        wholesaleBuyerCustomizeRequestId: Int
    ): Result<WholesaleBuyerCustomizeRequestDTO?>
}
