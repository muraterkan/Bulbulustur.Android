package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleMessageThreadParticipantDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleMessageThreadParticipantUpdateModel

interface IWholesaleMessageThreadParticipantRepository {

    suspend fun GetWholesaleMessageThreadParticipantListAsync(): Result<List<WholesaleMessageThreadParticipantDTO>>

    suspend fun GetWholesaleMessageThreadParticipantByIdAsync(
        wholesaleMessageThreadParticipantId: Int
    ): Result<WholesaleMessageThreadParticipantUpdateModel?>

    suspend fun GetWholesaleMessageThreadParticipantByIdExtendedAsync(
        wholesaleMessageThreadParticipantId: Int
    ): Result<WholesaleMessageThreadParticipantDTO?>
}
