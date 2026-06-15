package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleProductUpdateModel

interface IWholesaleProductRepository {

    suspend fun GetWholesaleProductListAsync(): Result<List<WholesaleProductDTO>>

    suspend fun GetWholesaleProductByIdAsync(
        wholesaleProductId: Int
    ): Result<WholesaleProductUpdateModel?>

    suspend fun GetWholesaleProductByIdExtendedAsync(
        wholesaleProductId: Int
    ): Result<WholesaleProductDTO?>
}
