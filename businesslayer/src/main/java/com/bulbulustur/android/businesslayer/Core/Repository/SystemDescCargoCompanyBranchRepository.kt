package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescCargoCompanyBranchDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescCargoCompanyBranchRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescCargoCompanyBranchUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class SystemDescCargoCompanyBranchRepository(
    private val apiClient: ApiClient
) : ISystemDescCargoCompanyBranchRepository {

    override suspend fun GetSystemDescCargoCompanyBranchListAsync(): Result<List<SystemDescCargoCompanyBranchDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescCargoCompanyBranchByIdAsync(
        systemDescCargoCompanyBranchId: Int
    ): Result<SystemDescCargoCompanyBranchUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescCargoCompanyBranchByIdExtendedAsync(
        systemDescCargoCompanyBranchId: Int
    ): Result<SystemDescCargoCompanyBranchDTO?> {
        TODO("Not implemented yet")
    }
}
