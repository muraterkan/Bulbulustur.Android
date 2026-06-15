package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ContractDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ContractUpdateModel

interface IContractRepository {

    suspend fun GetContractListAsync(): Result<List<ContractDTO>>

    suspend fun GetContractByIdAsync(
        contractId: Int
    ): Result<ContractUpdateModel?>

    suspend fun GetContractByIdExtendedAsync(
        contractId: Int
    ): Result<ContractDTO?>
}
