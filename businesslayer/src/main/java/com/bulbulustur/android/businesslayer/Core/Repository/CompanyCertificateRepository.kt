package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyCertificateDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICompanyCertificateRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyCertificateUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class CompanyCertificateRepository(
    private val apiClient: ApiClient
) : ICompanyCertificateRepository {

    override suspend fun GetCompanyCertificateListAsync(): Result<List<CompanyCertificateDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetCompanyCertificateByIdAsync(
        companyCertificateId: Int
    ): Result<CompanyCertificateUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetCompanyCertificateByIdExtendedAsync(
        companyCertificateId: Int
    ): Result<CompanyCertificateDTO?> {
        TODO("Not implemented yet")
    }
}
