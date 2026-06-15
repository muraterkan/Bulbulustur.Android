package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.ContractDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ContractInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ContractUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IContractRepository {

    @GET("api/Contract/GetContractListAsync")
    suspend fun GetContractListAsync():
            Result<List<ContractDTO>>

    @GET("api/Contract/GetContractByIdAsync")
    suspend fun GetContractByIdAsync(
        @Query("contractId")
        contractId: Int
    ): Result<ContractUpdateModel?>

    @GET("api/Contract/GetContractByIdExtendedAsync")
    suspend fun GetContractByIdExtendedAsync(
        @Query("contractId")
        contractId: Int
    ): Result<ContractDTO?>

    @POST("api/Contract/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: ContractInsertModel
    ): Result<Unit>

    @POST("api/Contract/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: ContractUpdateModel
    ): Result<Unit>

    @POST("api/Contract/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("contractId")
        contractId: Int
    ): Result<Unit>
}
