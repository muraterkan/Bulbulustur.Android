package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.ProductFavoriteDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductFavoriteRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductFavoriteInsertModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class ProductFavoriteRepository(
    private val apiClient: ApiClient = ApiClient
) : IProductFavoriteRepository {

    override suspend fun GetProductFavoritesAsync(memberId: Int, count: Int): Result<List<ProductFavoriteDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.B2C_PRODUCT_FAVORITE_BASE_URL,
            method = "GetProductFavoritesAsync",
            query = "memberId=$memberId&count=$count"
        )
    }

    override suspend fun InsertProductFavoriteAsync(memberId: Int, model: ProductFavoriteInsertModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.B2C_PRODUCT_FAVORITE_BASE_URL,
            method = "InsertProductFavoriteAsync?memberId=$memberId",
            data = model
        )
    }

    override suspend fun DeleteProductFavoriteAsync(memberId: Int, favoriteId: Int): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.B2C_PRODUCT_FAVORITE_BASE_URL,
            method = "DeleteProductFavoriteAsync",
            query = "memberId=$memberId&favoriteId=$favoriteId"
        )
    }

    override suspend fun MoveProductFavoriteToBasketAsync(memberId: Int, favoriteId: Int): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.B2C_PRODUCT_FAVORITE_BASE_URL,
            method = "MoveProductFavoriteToBasketAsync?memberId=$memberId&favoriteId=$favoriteId",
            data = Unit
        )
    }
}