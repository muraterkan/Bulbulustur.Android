package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductCategoryContentDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleProductCategoryContentRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleProductCategoryContentUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class WholesaleProductCategoryContentRepository(
    private val apiClient: ApiClient
) : IWholesaleProductCategoryContentRepository {

    override suspend fun GetWholesaleProductCategoryContentListAsync(): Result<List<WholesaleProductCategoryContentDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetWholesaleProductCategoryContentByIdAsync(
        wholesaleProductCategoryContentId: Int
    ): Result<WholesaleProductCategoryContentUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetWholesaleProductCategoryContentByIdExtendedAsync(
        wholesaleProductCategoryContentId: Int
    ): Result<WholesaleProductCategoryContentDTO?> {
        TODO("Not implemented yet")
    }
}
