package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.HelpFeedbackDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IHelpFeedbackRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.HelpFeedbackUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class HelpFeedbackRepository(
    private val apiClient: ApiClient
) : IHelpFeedbackRepository {

    override suspend fun GetHelpFeedbackListAsync(): Result<List<HelpFeedbackDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetHelpFeedbackByIdAsync(
        feedbackId: Int
    ): Result<HelpFeedbackUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetHelpFeedbackByIdExtendedAsync(
        feedbackId: Int
    ): Result<HelpFeedbackDTO?> {
        TODO("Not implemented yet")
    }
}
