package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescYesNoDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescYesNoUpdateModel

interface ISystemDescYesNoRepository {

    suspend fun GetSystemDescYesNoListAsync(): Result<List<SystemDescYesNoDTO>>

    suspend fun GetSystemDescYesNoByIdAsync(
        systemDescYesNoId: Int
    ): Result<SystemDescYesNoUpdateModel?>

    suspend fun GetSystemDescYesNoByIdExtendedAsync(
        systemDescYesNoId: Int
    ): Result<SystemDescYesNoDTO?>
}
