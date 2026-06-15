package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductCategoryContentGroupDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleProductCategoryContentGroupRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleProductCategoryContentGroupUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class WholesaleProductCategoryContentGroupRepository(
    private val apiClient: ApiClient
) : IWholesaleProductCategoryContentGroupRepository {

    override suspend fun GetWholesaleProductCategoryContentGroupListAsync(): Result<List<WholesaleProductCategoryContentGroupDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetWholesaleProductCategoryContentGroupByIdAsync(
        wholesaleProductCategoryContentGroupId: Int
    ): Result<WholesaleProductCategoryContentGroupUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetWholesaleProductCategoryContentGroupByIdExtendedAsync(
        wholesaleProductCategoryContentGroupId: Int
    ): Result<WholesaleProductCategoryContentGroupDTO?> {
        TODO("Not implemented yet")
    }
}
