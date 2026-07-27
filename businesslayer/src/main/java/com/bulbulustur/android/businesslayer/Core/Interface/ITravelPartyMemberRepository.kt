package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.TravelPartyMemberDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.TravelPartyMemberInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.TravelPartyMemberUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ITravelPartyMemberRepository {
    suspend fun GetTravelPartyMembersAsync(count: Int): Result<List<TravelPartyMemberDTO>>
    suspend fun GetTravelPartyMemberByIdAsync(travelPartyMemberId: Int): Result<TravelPartyMemberUpdateModel?>
    suspend fun GetTravelPartyMemberByIdExtendedAsync(travelPartyMemberId: Int): Result<TravelPartyMemberDTO?>
    suspend fun InsertAsync(model: TravelPartyMemberInsertModel): Result<Unit>
    suspend fun UpdateAsync(model: TravelPartyMemberUpdateModel): Result<Unit>
    suspend fun DeleteAsync(travelPartyMemberId: Int): Result<Unit>
}