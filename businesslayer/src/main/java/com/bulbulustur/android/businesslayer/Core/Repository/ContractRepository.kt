package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ContractDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IContractRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ContractUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class ContractRepository(
    private val apiClient: ApiClient
) : IContractRepository {

    override suspend fun GetContractListAsync(): Result<List<ContractDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetContractByIdAsync(
        contractId: Int
    ): Result<ContractUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetContractByIdExtendedAsync(
        contractId: Int
    ): Result<ContractDTO?> {
        TODO("Not implemented yet")
    }
}
