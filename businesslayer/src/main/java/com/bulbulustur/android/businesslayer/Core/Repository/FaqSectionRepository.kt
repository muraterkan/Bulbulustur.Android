package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.FaqSectionDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IFaqSectionRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.FaqSectionUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class FaqSectionRepository(
    private val apiClient: ApiClient
) : IFaqSectionRepository {

    override suspend fun GetFaqSectionListAsync(): Result<List<FaqSectionDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetFaqSectionByIdAsync(
        faqSectionId: Int
    ): Result<FaqSectionUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetFaqSectionByIdExtendedAsync(
        faqSectionId: Int
    ): Result<FaqSectionDTO?> {
        TODO("Not implemented yet")
    }
}
