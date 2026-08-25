package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICompanyRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result
import com.bulbulustur.android.businesslayer.Core.Util.PaginatedList

class CompanyRepository(private val apiClient: ApiClient = ApiClient) : ICompanyRepository {
    override suspend fun GetCompaniesAsync(languageId: Int, page: Int, pageSize: Int): Result<PaginatedList<CompanyDTO>> {
        return apiClient.GetAsync(baseUrl = ApiRoutes.COMMERCE_SUPPORT_BASE_URL, method = "Company/GetCompaniesAsync", query = "languageId=$languageId&page=$page&pageSize=$pageSize")
    }

    override suspend fun GetCompanyByIdExtendedAsync(languageId: Int, companyId: Int): Result<CompanyDTO?> {
        return apiClient.GetAsync(baseUrl = ApiRoutes.COMMERCE_SUPPORT_BASE_URL, method = "Company/GetCompanyByIdExtendedAsync", query = "languageId=$languageId&companyId=$companyId")
    }

    override suspend fun GetCompanyByMemberAsync(languageId: Int, memberId: Int): Result<CompanyDTO?> {
        return apiClient.GetAsync(baseUrl = ApiRoutes.COMMERCE_SUPPORT_BASE_URL, method = "Company/GetCompanyByMemberAsync", query = "languageId=$languageId&memberId=$memberId")
    }

    override suspend fun UpdateCompanyAsync(memberId: Int, updateModel: CompanyUpdateModel): Result<Any?> {
        return apiClient.PutAsync<CompanyUpdateModel, Any?>(baseUrl = ApiRoutes.COMMERCE_SUPPORT_BASE_URL, method = "Company/UpdateCompanyAsync", query = "memberId=$memberId", data = updateModel)
    }
}
