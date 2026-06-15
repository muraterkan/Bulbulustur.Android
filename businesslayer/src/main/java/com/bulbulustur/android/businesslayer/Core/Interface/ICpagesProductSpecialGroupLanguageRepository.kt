package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.CpagesProductSpecialGroupLanguageDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CpagesProductSpecialGroupLanguageInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CpagesProductSpecialGroupLanguageUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ICpagesProductSpecialGroupLanguageRepository {

    @GET("api/CpagesProductSpecialGroupLanguage/GetCpagesProductSpecialGroupLanguageListAsync")
    suspend fun GetCpagesProductSpecialGroupLanguageListAsync():
            Result<List<CpagesProductSpecialGroupLanguageDTO>>

    @GET("api/CpagesProductSpecialGroupLanguage/GetCpagesProductSpecialGroupLanguageByIdAsync")
    suspend fun GetCpagesProductSpecialGroupLanguageByIdAsync(
        @Query("cpagesProductSpecialGroupLanguageId")
        cpagesProductSpecialGroupLanguageId: Int
    ): Result<CpagesProductSpecialGroupLanguageUpdateModel?>

    @GET("api/CpagesProductSpecialGroupLanguage/GetCpagesProductSpecialGroupLanguageByIdExtendedAsync")
    suspend fun GetCpagesProductSpecialGroupLanguageByIdExtendedAsync(
        @Query("cpagesProductSpecialGroupLanguageId")
        cpagesProductSpecialGroupLanguageId: Int
    ): Result<CpagesProductSpecialGroupLanguageDTO?>

    @POST("api/CpagesProductSpecialGroupLanguage/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: CpagesProductSpecialGroupLanguageInsertModel
    ): Result<Unit>

    @POST("api/CpagesProductSpecialGroupLanguage/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: CpagesProductSpecialGroupLanguageUpdateModel
    ): Result<Unit>

    @POST("api/CpagesProductSpecialGroupLanguage/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("cpagesProductSpecialGroupLanguageId")
        cpagesProductSpecialGroupLanguageId: Int
    ): Result<Unit>
}
