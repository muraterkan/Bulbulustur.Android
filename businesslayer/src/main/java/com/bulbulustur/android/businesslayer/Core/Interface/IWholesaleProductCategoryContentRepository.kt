package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductCategoryContentDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleProductCategoryContentUpdateModel

interface IWholesaleProductCategoryContentRepository {

    suspend fun GetWholesaleProductCategoryContentListAsync(): Result<List<WholesaleProductCategoryContentDTO>>

    suspend fun GetWholesaleProductCategoryContentByIdAsync(
        wholesaleProductCategoryContentId: Int
    ): Result<WholesaleProductCategoryContentUpdateModel?>

    suspend fun GetWholesaleProductCategoryContentByIdExtendedAsync(
        wholesaleProductCategoryContentId: Int
    ): Result<WholesaleProductCategoryContentDTO?>
}
