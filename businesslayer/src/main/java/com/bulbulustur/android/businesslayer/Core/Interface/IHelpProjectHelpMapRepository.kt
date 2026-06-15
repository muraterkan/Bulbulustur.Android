package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.HelpProjectHelpMapDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.HelpProjectHelpMapUpdateModel

interface IHelpProjectHelpMapRepository {

    suspend fun GetHelpProjectHelpMapListAsync(): Result<List<HelpProjectHelpMapDTO>>

    suspend fun GetHelpProjectHelpMapByIdAsync(
        helpProjectHelpMapId: Int
    ): Result<HelpProjectHelpMapUpdateModel?>

    suspend fun GetHelpProjectHelpMapByIdExtendedAsync(
        helpProjectHelpMapId: Int
    ): Result<HelpProjectHelpMapDTO?>
}
