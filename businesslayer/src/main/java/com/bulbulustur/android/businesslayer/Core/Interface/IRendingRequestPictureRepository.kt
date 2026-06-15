package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ReturnRequestPictureDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ReturnRequestPictureUpdateModel

interface IReturnRequestPictureRepository {

    suspend fun GetReturnRequestPictureListAsync(): Result<List<ReturnRequestPictureDTO>>

    suspend fun GetReturnRequestPictureByIdAsync(
        returnRequestPictureId: Int
    ): Result<ReturnRequestPictureUpdateModel?>

    suspend fun GetReturnRequestPictureByIdExtendedAsync(
        returnRequestPictureId: Int
    ): Result<ReturnRequestPictureDTO?>
}
