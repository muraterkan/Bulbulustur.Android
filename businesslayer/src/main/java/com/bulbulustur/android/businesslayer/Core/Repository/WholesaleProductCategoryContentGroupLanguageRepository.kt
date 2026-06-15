package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductCategoryContentGroupLanguageDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleProductCategoryContentGroupLanguageRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleProductCategoryContentGroupLanguageUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class WholesaleProductCategoryContentGroupLanguageRepository(
    private val apiClient: ApiClient
) : IWholesaleProductCategoryContentGroupLanguageRepository {

    override suspend fun GetWholesaleProductCategoryContentGroupLanguageListAsync(): Result<List<WholesaleProductCategoryContentGroupLanguageDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetWholesaleProductCategoryContentGroupLanguageByIdAsync(
        wholesaleProductCategoryContentGroupLanguageId: Int
    ): Result<WholesaleProductCategoryContentGroupLanguageUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetWholesaleProductCategoryContentGroupLanguageByIdExtendedAsync(
        wholesaleProductCategoryContentGroupLanguageId: Int
    ): Result<WholesaleProductCategoryContentGroupLanguageDTO?> {
        TODO("Not implemented yet")
    }
}
