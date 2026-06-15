package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyVerificationDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICompanyVerificationRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyVerificationUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class CompanyVerificationRepository(
    private val apiClient: ApiClient
) : ICompanyVerificationRepository {

    override suspend fun GetCompanyVerificationListAsync(): Result<List<CompanyVerificationDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetCompanyVerificationByIdAsync(
        verificationId: Int
    ): Result<CompanyVerificationUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetCompanyVerificationByIdExtendedAsync(
        verificationId: Int
    ): Result<CompanyVerificationDTO?> {
        TODO("Not implemented yet")
    }
}
