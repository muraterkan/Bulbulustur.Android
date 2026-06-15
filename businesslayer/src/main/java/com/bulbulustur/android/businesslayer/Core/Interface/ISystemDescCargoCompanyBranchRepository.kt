package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescCargoCompanyBranchDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescCargoCompanyBranchUpdateModel

interface ISystemDescCargoCompanyBranchRepository {

    suspend fun GetSystemDescCargoCompanyBranchListAsync(): Result<List<SystemDescCargoCompanyBranchDTO>>

    suspend fun GetSystemDescCargoCompanyBranchByIdAsync(
        systemDescCargoCompanyBranchId: Int
    ): Result<SystemDescCargoCompanyBranchUpdateModel?>

    suspend fun GetSystemDescCargoCompanyBranchByIdExtendedAsync(
        systemDescCargoCompanyBranchId: Int
    ): Result<SystemDescCargoCompanyBranchDTO?>
}
