package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ReturnRequestPictureDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IReturnRequestPictureRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ReturnRequestPictureUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class ReturnRequestPictureRepository(
    private val apiClient: ApiClient
) : IReturnRequestPictureRepository {

    override suspend fun GetReturnRequestPictureListAsync(): Result<List<ReturnRequestPictureDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetReturnRequestPictureByIdAsync(
        returnRequestPictureId: Int
    ): Result<ReturnRequestPictureUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetReturnRequestPictureByIdExtendedAsync(
        returnRequestPictureId: Int
    ): Result<ReturnRequestPictureDTO?> {
        TODO("Not implemented yet")
    }
}
