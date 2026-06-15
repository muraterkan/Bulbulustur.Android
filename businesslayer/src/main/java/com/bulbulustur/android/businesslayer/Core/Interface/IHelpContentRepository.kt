package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.HelpContentDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.HelpContentUpdateModel

interface IHelpContentRepository {

    suspend fun GetHelpContentListAsync(): Result<List<HelpContentDTO>>

    suspend fun GetHelpContentByIdAsync(
        helpContentId: Int
    ): Result<HelpContentUpdateModel?>

    suspend fun GetHelpContentByIdExtendedAsync(
        helpContentId: Int
    ): Result<HelpContentDTO?>
}
