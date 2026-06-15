package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.AddressCountryDepartmentDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IAddressCountryDepartmentRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.AddressCountryDepartmentInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.AddressCountryDepartmentUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class AddressCountryDepartmentRepository(
    private val apiClient: ApiClient = ApiClient
) : IAddressCountryDepartmentRepository {

    override suspend fun GetAddressCountryDepartmentListAsync(): Result<List<AddressCountryDepartmentDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetAddressCountryDepartmentListAsync"
        )
    }

    override suspend fun GetAddressCountryDepartmentByIdAsync(
        addressCountryDepartmentId: Int
    ): Result<AddressCountryDepartmentUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetAddressCountryDepartmentByIdAsync",
            query = "addressCountryDepartmentId=$addressCountryDepartmentId"
        )
    }

    override suspend fun GetAddressCountryDepartmentByIdExtendedAsync(
        addressCountryDepartmentId: Int
    ): Result<AddressCountryDepartmentDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetAddressCountryDepartmentByIdExtendedAsync",
            query = "addressCountryDepartmentId=$addressCountryDepartmentId"
        )
    }

    override suspend fun InsertAsync(
        model: AddressCountryDepartmentInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: AddressCountryDepartmentUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        addressCountryDepartmentId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "addressCountryDepartmentId=$addressCountryDepartmentId"
        )
    }
}