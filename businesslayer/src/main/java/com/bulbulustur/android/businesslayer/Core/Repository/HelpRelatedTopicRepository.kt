package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.HelpRelatedTopicDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IHelpRelatedTopicRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.HelpRelatedTopicUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class HelpRelatedTopicRepository(
    private val apiClient: ApiClient
) : IHelpRelatedTopicRepository {

    override suspend fun GetHelpRelatedTopicListAsync(): Result<List<HelpRelatedTopicDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetHelpRelatedTopicByIdAsync(
        helpRelatedTopicId: Int
    ): Result<HelpRelatedTopicUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetHelpRelatedTopicByIdExtendedAsync(
        helpRelatedTopicId: Int
    ): Result<HelpRelatedTopicDTO?> {
        TODO("Not implemented yet")
    }
}
