package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.HelpCategoryDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.HelpCategoryUpdateModel

interface IHelpCategoryRepository {

    suspend fun GetHelpCategoryListAsync(): Result<List<HelpCategoryDTO>>

    suspend fun GetHelpCategoryByIdAsync(
        helpCategoryId: Int
    ): Result<HelpCategoryUpdateModel?>

    suspend fun GetHelpCategoryByIdExtendedAsync(
        helpCategoryId: Int
    ): Result<HelpCategoryDTO?>
}
