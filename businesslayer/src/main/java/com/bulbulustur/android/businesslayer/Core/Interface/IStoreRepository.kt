package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.StoreDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.StoreInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.StoreUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.PaginatedList
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IStoreRepository {

    @GET("api/Store/GetStoresAsync")
    suspend fun GetStoresAsync(
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 100
    ): Result<PaginatedList<StoreDTO>>

    @GET("api/Store/GetStoreByIdAsync")
    suspend fun GetStoreByIdAsync(
        @Query("storeId")
        storeId: Int
    ): Result<StoreUpdateModel?>

    @GET("api/Store/GetStoreByIdExtendedAsync")
    suspend fun GetStoreByIdExtendedAsync(
        @Query("storeId")
        storeId: Int
    ): Result<StoreDTO?>

    @POST("api/Store/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: StoreInsertModel
    ): Result<Unit>

    @POST("api/Store/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: StoreUpdateModel
    ): Result<Unit>

    @POST("api/Store/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("storeId")
        storeId: Int
    ): Result<Unit>
}
