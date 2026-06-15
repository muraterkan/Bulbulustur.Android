package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescCertificateTypeDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescCertificateTypeRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescCertificateTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class SystemDescCertificateTypeRepository(
    private val apiClient: ApiClient
) : ISystemDescCertificateTypeRepository {

    override suspend fun GetSystemDescCertificateTypeListAsync(): Result<List<SystemDescCertificateTypeDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescCertificateTypeByIdAsync(
        systemDescCertificateTypeId: Int
    ): Result<SystemDescCertificateTypeUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescCertificateTypeByIdExtendedAsync(
        systemDescCertificateTypeId: Int
    ): Result<SystemDescCertificateTypeDTO?> {
        TODO("Not implemented yet")
    }
}
