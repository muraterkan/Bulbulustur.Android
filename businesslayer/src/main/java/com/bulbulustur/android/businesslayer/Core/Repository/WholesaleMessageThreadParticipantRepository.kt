package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleMessageThreadParticipantDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleMessageThreadParticipantRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleMessageThreadParticipantUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class WholesaleMessageThreadParticipantRepository(
    private val apiClient: ApiClient
) : IWholesaleMessageThreadParticipantRepository {

    override suspend fun GetWholesaleMessageThreadParticipantListAsync(): Result<List<WholesaleMessageThreadParticipantDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetWholesaleMessageThreadParticipantByIdAsync(
        wholesaleMessageThreadParticipantId: Int
    ): Result<WholesaleMessageThreadParticipantUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetWholesaleMessageThreadParticipantByIdExtendedAsync(
        wholesaleMessageThreadParticipantId: Int
    ): Result<WholesaleMessageThreadParticipantDTO?> {
        TODO("Not implemented yet")
    }
}
