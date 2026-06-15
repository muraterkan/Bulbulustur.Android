package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.HelpContentDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IHelpContentRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.HelpContentUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class HelpContentRepository(
    private val apiClient: ApiClient
) : IHelpContentRepository {

    override suspend fun GetHelpContentListAsync(): Result<List<HelpContentDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetHelpContentByIdAsync(
        helpContentId: Int
    ): Result<HelpContentUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetHelpContentByIdExtendedAsync(
        helpContentId: Int
    ): Result<HelpContentDTO?> {
        TODO("Not implemented yet")
    }
}
