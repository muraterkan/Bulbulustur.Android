package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.HelpPopularTopicDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IHelpPopularTopicRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.HelpPopularTopicUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class HelpPopularTopicRepository(
    private val apiClient: ApiClient
) : IHelpPopularTopicRepository {

    override suspend fun GetHelpPopularTopicListAsync(): Result<List<HelpPopularTopicDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetHelpPopularTopicByIdAsync(
        helpPopularTopicId: Int
    ): Result<HelpPopularTopicUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetHelpPopularTopicByIdExtendedAsync(
        helpPopularTopicId: Int
    ): Result<HelpPopularTopicDTO?> {
        TODO("Not implemented yet")
    }
}
