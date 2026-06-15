package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductCertificateDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleProductCertificateRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleProductCertificateUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class WholesaleProductCertificateRepository(
    private val apiClient: ApiClient
) : IWholesaleProductCertificateRepository {

    override suspend fun GetWholesaleProductCertificateListAsync(): Result<List<WholesaleProductCertificateDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetWholesaleProductCertificateByIdAsync(
        wholesaleProductCertificateId: Int
    ): Result<WholesaleProductCertificateUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetWholesaleProductCertificateByIdExtendedAsync(
        wholesaleProductCertificateId: Int
    ): Result<WholesaleProductCertificateDTO?> {
        TODO("Not implemented yet")
    }
}
