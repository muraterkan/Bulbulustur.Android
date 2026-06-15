package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.HelpProjectDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IHelpProjectRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.HelpProjectUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class HelpProjectRepository(
    private val apiClient: ApiClient
) : IHelpProjectRepository {

    override suspend fun GetHelpProjectListAsync(): Result<List<HelpProjectDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetHelpProjectByIdAsync(
        helpProjectId: Int
    ): Result<HelpProjectUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetHelpProjectByIdExtendedAsync(
        helpProjectId: Int
    ): Result<HelpProjectDTO?> {
        TODO("Not implemented yet")
    }
}
