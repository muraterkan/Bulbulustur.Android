package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.StoreAddressDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.StoreAddressInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.StoreAddressUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IStoreAddressRepository {

    @GET("api/StoreAddress/GetStoreAddressListAsync")
    suspend fun GetStoreAddressListAsync():
            Result<List<StoreAddressDTO>>

    @GET("api/StoreAddress/GetStoreAddressByIdAsync")
    suspend fun GetStoreAddressByIdAsync(
        @Query("storeAddressId")
        storeAddressId: Int
    ): Result<StoreAddressUpdateModel?>

    @GET("api/StoreAddress/GetStoreAddressByIdExtendedAsync")
    suspend fun GetStoreAddressByIdExtendedAsync(
        @Query("storeAddressId")
        storeAddressId: Int
    ): Result<StoreAddressDTO?>

    @POST("api/StoreAddress/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: StoreAddressInsertModel
    ): Result<Unit>

    @POST("api/StoreAddress/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: StoreAddressUpdateModel
    ): Result<Unit>

    @POST("api/StoreAddress/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("storeAddressId")
        storeAddressId: Int
    ): Result<Unit>
}
