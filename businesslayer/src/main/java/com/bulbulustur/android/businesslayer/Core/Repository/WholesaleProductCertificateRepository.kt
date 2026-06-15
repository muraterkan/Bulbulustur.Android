package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductCertificateDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleProductCertificateRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.WholesaleProductCertificateInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleProductCertificateUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class WholesaleProductCertificateRepository(
    private val apiClient: ApiClient = ApiClient
) : IWholesaleProductCertificateRepository {

    override suspend fun GetWholesaleProductCertificateListAsync(): Result<List<WholesaleProductCertificateDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetWholesaleProductCertificateListAsync"
        )
    }

    override suspend fun GetWholesaleProductCertificateByIdAsync(
        wholesaleProductCertificateId: Int
    ): Result<WholesaleProductCertificateUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetWholesaleProductCertificateByIdAsync",
            query = "wholesaleProductCertificateId=$wholesaleProductCertificateId"
        )
    }

    override suspend fun GetWholesaleProductCertificateByIdExtendedAsync(
        wholesaleProductCertificateId: Int
    ): Result<WholesaleProductCertificateDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetWholesaleProductCertificateByIdExtendedAsync",
            query = "wholesaleProductCertificateId=$wholesaleProductCertificateId"
        )
    }

    override suspend fun InsertAsync(
        model: WholesaleProductCertificateInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: WholesaleProductCertificateUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        wholesaleProductCertificateId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "wholesaleProductCertificateId=$wholesaleProductCertificateId"
        )
    }
}