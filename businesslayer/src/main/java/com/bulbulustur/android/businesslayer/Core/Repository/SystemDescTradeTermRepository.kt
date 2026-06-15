package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescTradeTermDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescTradeTermRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescTradeTermUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class SystemDescTradeTermRepository(
    private val apiClient: ApiClient
) : ISystemDescTradeTermRepository {

    override suspend fun GetSystemDescTradeTermListAsync(): Result<List<SystemDescTradeTermDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescTradeTermByIdAsync(
        systemDescTradeTermId: Int
    ): Result<SystemDescTradeTermUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescTradeTermByIdExtendedAsync(
        systemDescTradeTermId: Int
    ): Result<SystemDescTradeTermDTO?> {
        TODO("Not implemented yet")
    }
}
