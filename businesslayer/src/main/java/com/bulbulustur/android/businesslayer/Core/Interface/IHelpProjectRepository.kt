package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.HelpProjectDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.HelpProjectUpdateModel

interface IHelpProjectRepository {

    suspend fun GetHelpProjectListAsync(): Result<List<HelpProjectDTO>>

    suspend fun GetHelpProjectByIdAsync(
        helpProjectId: Int
    ): Result<HelpProjectUpdateModel?>

    suspend fun GetHelpProjectByIdExtendedAsync(
        helpProjectId: Int
    ): Result<HelpProjectDTO?>
}
