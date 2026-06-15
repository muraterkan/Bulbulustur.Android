package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescCargoCompanyBranchDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescCargoCompanyBranchInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescCargoCompanyBranchUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ISystemDescCargoCompanyBranchRepository {

    @GET("api/SystemDescCargoCompanyBranch/GetSystemDescCargoCompanyBranchListAsync")
    suspend fun GetSystemDescCargoCompanyBranchListAsync():
            Result<List<SystemDescCargoCompanyBranchDTO>>

    @GET("api/SystemDescCargoCompanyBranch/GetSystemDescCargoCompanyBranchByIdAsync")
    suspend fun GetSystemDescCargoCompanyBranchByIdAsync(
        @Query("systemDescCargoCompanyBranchId")
        systemDescCargoCompanyBranchId: Int
    ): Result<SystemDescCargoCompanyBranchUpdateModel?>

    @GET("api/SystemDescCargoCompanyBranch/GetSystemDescCargoCompanyBranchByIdExtendedAsync")
    suspend fun GetSystemDescCargoCompanyBranchByIdExtendedAsync(
        @Query("systemDescCargoCompanyBranchId")
        systemDescCargoCompanyBranchId: Int
    ): Result<SystemDescCargoCompanyBranchDTO?>

    @POST("api/SystemDescCargoCompanyBranch/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: SystemDescCargoCompanyBranchInsertModel
    ): Result<Unit>

    @POST("api/SystemDescCargoCompanyBranch/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: SystemDescCargoCompanyBranchUpdateModel
    ): Result<Unit>

    @POST("api/SystemDescCargoCompanyBranch/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("systemDescCargoCompanyBranchId")
        systemDescCargoCompanyBranchId: Int
    ): Result<Unit>
}
