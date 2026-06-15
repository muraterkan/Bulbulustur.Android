package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductPictureDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleProductPictureRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleProductPictureUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class WholesaleProductPictureRepository(
    private val apiClient: ApiClient
) : IWholesaleProductPictureRepository {

    override suspend fun GetWholesaleProductPictureListAsync(): Result<List<WholesaleProductPictureDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetWholesaleProductPictureByIdAsync(
        wholesaleProductPictureId: Int
    ): Result<WholesaleProductPictureUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetWholesaleProductPictureByIdExtendedAsync(
        wholesaleProductPictureId: Int
    ): Result<WholesaleProductPictureDTO?> {
        TODO("Not implemented yet")
    }
}
