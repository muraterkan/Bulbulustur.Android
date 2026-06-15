package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.FaqDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IFaqRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.FaqUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class FaqRepository(
    private val apiClient: ApiClient
) : IFaqRepository {

    override suspend fun GetFaqListAsync(): Result<List<FaqDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetFaqByIdAsync(
        faqId: Int
    ): Result<FaqUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetFaqByIdExtendedAsync(
        faqId: Int
    ): Result<FaqDTO?> {
        TODO("Not implemented yet")
    }
}
