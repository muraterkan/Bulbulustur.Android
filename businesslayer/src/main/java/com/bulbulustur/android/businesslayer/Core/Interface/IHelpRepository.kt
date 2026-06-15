package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.HelpDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.HelpUpdateModel

interface IHelpRepository {

    suspend fun GetHelpListAsync(): Result<List<HelpDTO>>

    suspend fun GetHelpByIdAsync(
        helpId: Int
    ): Result<HelpUpdateModel?>

    suspend fun GetHelpByIdExtendedAsync(
        helpId: Int
    ): Result<HelpDTO?>
}
