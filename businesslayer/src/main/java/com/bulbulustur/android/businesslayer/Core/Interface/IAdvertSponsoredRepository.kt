package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.AdvertSponsoredDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.AdvertSponsoredInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.AdvertSponsoredUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IAdvertSponsoredRepository {

    @GET("api/AdvertSponsored/GetAdvertSponsoredListAsync")
    suspend fun GetAdvertSponsoredListAsync():
            Result<List<AdvertSponsoredDTO>>

    @GET("api/AdvertSponsored/GetAdvertSponsoredByIdAsync")
    suspend fun GetAdvertSponsoredByIdAsync(
        @Query("advertSponsoredId")
        advertSponsoredId: Int
    ): Result<AdvertSponsoredUpdateModel?>

    @GET("api/AdvertSponsored/GetAdvertSponsoredByIdExtendedAsync")
    suspend fun GetAdvertSponsoredByIdExtendedAsync(
        @Query("advertSponsoredId")
        advertSponsoredId: Int
    ): Result<AdvertSponsoredDTO?>

    @POST("api/AdvertSponsored/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: AdvertSponsoredInsertModel
    ): Result<Unit>

    @POST("api/AdvertSponsored/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: AdvertSponsoredUpdateModel
    ): Result<Unit>

    @POST("api/AdvertSponsored/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("advertSponsoredId")
        advertSponsoredId: Int
    ): Result<Unit>
}
