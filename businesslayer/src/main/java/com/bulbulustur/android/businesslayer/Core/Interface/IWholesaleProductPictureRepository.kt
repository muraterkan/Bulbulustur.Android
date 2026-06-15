package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductPictureDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleProductPictureUpdateModel

interface IWholesaleProductPictureRepository {

    suspend fun GetWholesaleProductPictureListAsync(): Result<List<WholesaleProductPictureDTO>>

    suspend fun GetWholesaleProductPictureByIdAsync(
        wholesaleProductPictureId: Int
    ): Result<WholesaleProductPictureUpdateModel?>

    suspend fun GetWholesaleProductPictureByIdExtendedAsync(
        wholesaleProductPictureId: Int
    ): Result<WholesaleProductPictureDTO?>
}
