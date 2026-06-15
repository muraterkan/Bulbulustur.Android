package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductCategoryContentGroupLanguageDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleProductCategoryContentGroupLanguageUpdateModel

interface IWholesaleProductCategoryContentGroupLanguageRepository {

    suspend fun GetWholesaleProductCategoryContentGroupLanguageListAsync(): Result<List<WholesaleProductCategoryContentGroupLanguageDTO>>

    suspend fun GetWholesaleProductCategoryContentGroupLanguageByIdAsync(
        wholesaleProductCategoryContentGroupLanguageId: Int
    ): Result<WholesaleProductCategoryContentGroupLanguageUpdateModel?>

    suspend fun GetWholesaleProductCategoryContentGroupLanguageByIdExtendedAsync(
        wholesaleProductCategoryContentGroupLanguageId: Int
    ): Result<WholesaleProductCategoryContentGroupLanguageDTO?>
}
