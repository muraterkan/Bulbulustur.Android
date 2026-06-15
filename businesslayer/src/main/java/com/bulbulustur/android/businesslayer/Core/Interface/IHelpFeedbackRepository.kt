package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.HelpFeedbackDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.HelpFeedbackUpdateModel

interface IHelpFeedbackRepository {

    suspend fun GetHelpFeedbackListAsync(): Result<List<HelpFeedbackDTO>>

    suspend fun GetHelpFeedbackByIdAsync(
        feedbackId: Int
    ): Result<HelpFeedbackUpdateModel?>

    suspend fun GetHelpFeedbackByIdExtendedAsync(
        feedbackId: Int
    ): Result<HelpFeedbackDTO?>
}
