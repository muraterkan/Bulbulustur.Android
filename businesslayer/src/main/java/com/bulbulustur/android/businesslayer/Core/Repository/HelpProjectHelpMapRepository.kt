package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.HelpProjectHelpMapDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IHelpProjectHelpMapRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.HelpProjectHelpMapUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class HelpProjectHelpMapRepository(
    private val apiClient: ApiClient
) : IHelpProjectHelpMapRepository {

    override suspend fun GetHelpProjectHelpMapListAsync(): Result<List<HelpProjectHelpMapDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetHelpProjectHelpMapByIdAsync(
        helpProjectHelpMapId: Int
    ): Result<HelpProjectHelpMapUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetHelpProjectHelpMapByIdExtendedAsync(
        helpProjectHelpMapId: Int
    ): Result<HelpProjectHelpMapDTO?> {
        TODO("Not implemented yet")
    }
}
