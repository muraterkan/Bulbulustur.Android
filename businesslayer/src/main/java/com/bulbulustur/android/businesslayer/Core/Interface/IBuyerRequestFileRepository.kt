package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.BuyerRequestFileDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.BuyerRequestFileUpdateModel

interface IBuyerRequestFileRepository {

    suspend fun GetBuyerRequestFileListAsync(): Result<List<BuyerRequestFileDTO>>

    suspend fun GetBuyerRequestFileByIdAsync(
        buyerRequestFileId: Int
    ): Result<BuyerRequestFileUpdateModel?>

    suspend fun GetBuyerRequestFileByIdExtendedAsync(
        buyerRequestFileId: Int
    ): Result<BuyerRequestFileDTO?>
}
