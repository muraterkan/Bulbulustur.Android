package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescYesNoDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescYesNoRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescYesNoUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class SystemDescYesNoRepository(
    private val apiClient: ApiClient
) : ISystemDescYesNoRepository {

    override suspend fun GetSystemDescYesNoListAsync(): Result<List<SystemDescYesNoDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescYesNoByIdAsync(
        systemDescYesNoId: Int
    ): Result<SystemDescYesNoUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescYesNoByIdExtendedAsync(
        systemDescYesNoId: Int
    ): Result<SystemDescYesNoDTO?> {
        TODO("Not implemented yet")
    }
}
