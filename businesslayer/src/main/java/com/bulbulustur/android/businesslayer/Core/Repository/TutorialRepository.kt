package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.TutorialDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ITutorialRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.TutorialUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class TutorialRepository(
    private val apiClient: ApiClient
) : ITutorialRepository {

    override suspend fun GetTutorialListAsync(): Result<List<TutorialDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetTutorialByIdAsync(
        tutorialId: Int
    ): Result<TutorialUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetTutorialByIdExtendedAsync(
        tutorialId: Int
    ): Result<TutorialDTO?> {
        TODO("Not implemented yet")
    }
}
