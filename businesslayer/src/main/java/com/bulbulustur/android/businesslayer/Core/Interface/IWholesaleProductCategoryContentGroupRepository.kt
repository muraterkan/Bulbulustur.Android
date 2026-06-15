package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductCategoryContentGroupDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleProductCategoryContentGroupUpdateModel

interface IWholesaleProductCategoryContentGroupRepository {

    suspend fun GetWholesaleProductCategoryContentGroupListAsync(): Result<List<WholesaleProductCategoryContentGroupDTO>>

    suspend fun GetWholesaleProductCategoryContentGroupByIdAsync(
        wholesaleProductCategoryContentGroupId: Int
    ): Result<WholesaleProductCategoryContentGroupUpdateModel?>

    suspend fun GetWholesaleProductCategoryContentGroupByIdExtendedAsync(
        wholesaleProductCategoryContentGroupId: Int
    ): Result<WholesaleProductCategoryContentGroupDTO?>
}
