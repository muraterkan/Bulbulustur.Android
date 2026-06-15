package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyExtendedInformationDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICompanyExtendedInformationRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyExtendedInformationUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class CompanyExtendedInformationRepository(
    private val apiClient: ApiClient
) : ICompanyExtendedInformationRepository {

    override suspend fun GetCompanyExtendedInformationListAsync(): Result<List<CompanyExtendedInformationDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetCompanyExtendedInformationByIdAsync(
        extendedInformationId: Int
    ): Result<CompanyExtendedInformationUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetCompanyExtendedInformationByIdExtendedAsync(
        extendedInformationId: Int
    ): Result<CompanyExtendedInformationDTO?> {
        TODO("Not implemented yet")
    }
}
