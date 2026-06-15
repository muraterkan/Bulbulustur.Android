package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.AdvertDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.AdvertInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.AdvertUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IAdvertRepository {

    @GET("api/Advert/GetAdvertListAsync")
    suspend fun GetAdvertListAsync():
            Result<List<AdvertDTO>>

    @GET("api/Advert/GetAdvertByIdAsync")
    suspend fun GetAdvertByIdAsync(
        @Query("advertId")
        advertId: Int
    ): Result<AdvertUpdateModel?>

    @GET("api/Advert/GetAdvertByIdExtendedAsync")
    suspend fun GetAdvertByIdExtendedAsync(
        @Query("advertId")
        advertId: Int
    ): Result<AdvertDTO?>

    @POST("api/Advert/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: AdvertInsertModel
    ): Result<Unit>

    @POST("api/Advert/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: AdvertUpdateModel
    ): Result<Unit>

    @POST("api/Advert/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("advertId")
        advertId: Int
    ): Result<Unit>
}
