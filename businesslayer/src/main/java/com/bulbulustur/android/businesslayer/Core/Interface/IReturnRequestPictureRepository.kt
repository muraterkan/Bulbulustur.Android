package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.ReturnRequestPictureDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ReturnRequestPictureInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ReturnRequestPictureUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IReturnRequestPictureRepository {

    suspend fun GetReturnRequestPictureListAsync():
            Result<List<ReturnRequestPictureDTO>>

    suspend fun GetReturnRequestPictureByIdAsync(
        ReturnRequestPictureId: Int
    ): Result<ReturnRequestPictureUpdateModel?>

    suspend fun GetReturnRequestPictureByIdExtendedAsync(
        ReturnRequestPictureId: Int
    ): Result<ReturnRequestPictureDTO?>

    suspend fun InsertAsync(
        model: ReturnRequestPictureInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: ReturnRequestPictureUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        ReturnRequestPictureId: Int
    ): Result<Unit>
}