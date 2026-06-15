package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.HelpCategoryDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IHelpCategoryRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.HelpCategoryUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class HelpCategoryRepository(
    private val apiClient: ApiClient
) : IHelpCategoryRepository {

    override suspend fun GetHelpCategoryListAsync(): Result<List<HelpCategoryDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetHelpCategoryByIdAsync(
        helpCategoryId: Int
    ): Result<HelpCategoryUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetHelpCategoryByIdExtendedAsync(
        helpCategoryId: Int
    ): Result<HelpCategoryDTO?> {
        TODO("Not implemented yet")
    }
}
