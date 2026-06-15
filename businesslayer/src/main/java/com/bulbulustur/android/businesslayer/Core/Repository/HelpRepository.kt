package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.HelpDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IHelpRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.HelpUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class HelpRepository(
    private val apiClient: ApiClient
) : IHelpRepository {

    override suspend fun GetHelpListAsync(): Result<List<HelpDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetHelpByIdAsync(
        helpId: Int
    ): Result<HelpUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetHelpByIdExtendedAsync(
        helpId: Int
    ): Result<HelpDTO?> {
        TODO("Not implemented yet")
    }
}
