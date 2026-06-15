package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.BuyerRequestFileDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IBuyerRequestFileRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.BuyerRequestFileUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class BuyerRequestFileRepository(
    private val apiClient: ApiClient
) : IBuyerRequestFileRepository {

    override suspend fun GetBuyerRequestFileListAsync(): Result<List<BuyerRequestFileDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetBuyerRequestFileByIdAsync(
        buyerRequestFileId: Int
    ): Result<BuyerRequestFileUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetBuyerRequestFileByIdExtendedAsync(
        buyerRequestFileId: Int
    ): Result<BuyerRequestFileDTO?> {
        TODO("Not implemented yet")
    }
}
