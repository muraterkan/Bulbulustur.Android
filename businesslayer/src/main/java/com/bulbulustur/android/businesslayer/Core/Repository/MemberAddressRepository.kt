package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.MemberAddressDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberAddressRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberAddressInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberAddressUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class MemberAddressRepository(private val apiClient: ApiClient = ApiClient) : IMemberAddressRepository {

    override suspend fun GetAccountAddressesAsync(memberId: Int, count: Int): Result<List<MemberAddressDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_ACCOUNT_BASE_URL,
            method = "GetAccountAddressesAsync",
            query = "memberId=$memberId&count=$count"
        )
    }

    override suspend fun GetAccountAddressByIdAsync(memberId: Int, addressKey: String): Result<MemberAddressUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_ACCOUNT_BASE_URL,
            method = "GetAccountAddressByIdAsync",
            query = "memberId=$memberId&addressKey=$addressKey"
        )
    }

    override suspend fun InsertAccountAddressAsync(memberId: Int, model: MemberAddressInsertModel): Result<Unit> {
        return apiClient.PostAsync<MemberAddressInsertModel, Unit>(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_ACCOUNT_BASE_URL,
            method = "InsertAccountAddressAsync",
            data = model,
            query = "memberId=$memberId"
        )
    }

    override suspend fun UpdateAccountAddressAsync(memberId: Int, model: MemberAddressUpdateModel): Result<Unit> {
        return apiClient.PutAsync<MemberAddressUpdateModel, Unit>(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_ACCOUNT_BASE_URL,
            method = "UpdateAccountAddressAsync",
            data = model,
            query = "memberId=$memberId"
        )
    }

    override suspend fun DeleteAccountAddressAsync(memberId: Int, addressId: Int): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_ACCOUNT_BASE_URL,
            method = "DeleteAccountAddress",
            query = "memberId=$memberId&addressId=$addressId"
        )
    }
}