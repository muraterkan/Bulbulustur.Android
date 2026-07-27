package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.TravelPartyMemberDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ITravelPartyMemberRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.TravelPartyMemberInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.TravelPartyMemberUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class TravelPartyMemberRepository(private val apiClient: ApiClient = ApiClient) : ITravelPartyMemberRepository {

    override suspend fun GetTravelPartyMembersAsync(count: Int): Result<List<TravelPartyMemberDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "GetTravelPartyMembersAsync",
            query = "count=$count"
        )
    }

    override suspend fun GetTravelPartyMemberByIdAsync(travelPartyMemberId: Int): Result<TravelPartyMemberUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "GetTravelPartyMemberByIdAsync",
            query = "travelPartyMemberId=$travelPartyMemberId"
        )
    }

    override suspend fun GetTravelPartyMemberByIdExtendedAsync(travelPartyMemberId: Int): Result<TravelPartyMemberDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "GetTravelPartyMemberByIdExtendedAsync",
            query = "travelPartyMemberId=$travelPartyMemberId"
        )
    }

    override suspend fun InsertAsync(model: TravelPartyMemberInsertModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "InsertTravelPartyMemberAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(model: TravelPartyMemberUpdateModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "UpdateTravelPartyMemberAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(travelPartyMemberId: Int): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "DeleteTravelPartyMemberAsync",
            query = "travelPartyMemberId=$travelPartyMemberId"
        )
    }
}