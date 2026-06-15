package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescGenderDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescGenderInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescGenderUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ISystemDescGenderRepository {

    @GET("api/SystemDescGender/GetSystemDescGenderListAsync")
    suspend fun GetSystemDescGenderListAsync():
            Result<List<SystemDescGenderDTO>>

    @GET("api/SystemDescGender/GetSystemDescGenderByIdAsync")
    suspend fun GetSystemDescGenderByIdAsync(
        @Query("systemDescGenderId")
        systemDescGenderId: Int
    ): Result<SystemDescGenderUpdateModel?>

    @GET("api/SystemDescGender/GetSystemDescGenderByIdExtendedAsync")
    suspend fun GetSystemDescGenderByIdExtendedAsync(
        @Query("systemDescGenderId")
        systemDescGenderId: Int
    ): Result<SystemDescGenderDTO?>

    @POST("api/SystemDescGender/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: SystemDescGenderInsertModel
    ): Result<Unit>

    @POST("api/SystemDescGender/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: SystemDescGenderUpdateModel
    ): Result<Unit>

    @POST("api/SystemDescGender/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("systemDescGenderId")
        systemDescGenderId: Int
    ): Result<Unit>
}
