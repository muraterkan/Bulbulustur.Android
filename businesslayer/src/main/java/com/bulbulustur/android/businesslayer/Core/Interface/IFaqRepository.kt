package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.FaqDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.FaqUpdateModel

interface IFaqRepository {

    suspend fun GetFaqListAsync(): Result<List<FaqDTO>>

    suspend fun GetFaqByIdAsync(
        faqId: Int
    ): Result<FaqUpdateModel?>

    suspend fun GetFaqByIdExtendedAsync(
        faqId: Int
    ): Result<FaqDTO?>
}
