package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.FaqSectionDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.FaqSectionUpdateModel

interface IFaqSectionRepository {

    suspend fun GetFaqSectionListAsync(): Result<List<FaqSectionDTO>>

    suspend fun GetFaqSectionByIdAsync(
        faqSectionId: Int
    ): Result<FaqSectionUpdateModel?>

    suspend fun GetFaqSectionByIdExtendedAsync(
        faqSectionId: Int
    ): Result<FaqSectionDTO?>
}
