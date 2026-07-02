package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.MemberFollowedStoreDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberFollowedStoreInsertModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IMemberFollowedStoreRepository {

    suspend fun GetAccountFollowedStores(memberId: Int, count: Int): Result<List<MemberFollowedStoreDTO>>

    suspend fun InsertAccountFollowedStoreAsync(memberId: Int, model: MemberFollowedStoreInsertModel): Result<Unit>

    suspend fun DeleteAccountFollowedStoreAsync(memberId: Int, followedStoreId: Int): Result<Unit>
}