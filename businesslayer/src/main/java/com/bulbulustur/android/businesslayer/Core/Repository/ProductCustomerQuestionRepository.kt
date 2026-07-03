package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.ProductCustomerQuestionDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductCustomerQuestionRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductCustomerQuestionInsertModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class ProductCustomerQuestionRepository(private val apiClient: ApiClient = ApiClient) : IProductCustomerQuestionRepository {

    override suspend fun GetProductCustomerQuestionsAsync(productId: Int, count: Int): Result<List<ProductCustomerQuestionDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.B2C_PRODUCT_QUESTION_BASE_URL,
            method = "GetProductCustomerQuestionsAsync",
            query = "productId=$productId&count=$count"
        )
    }

    override suspend fun GetMemberProductCustomerQuestionsAsync(memberId: Int, count: Int): Result<List<ProductCustomerQuestionDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.B2C_PRODUCT_QUESTION_BASE_URL,
            method = "GetMemberProductCustomerQuestionsAsync",
            query = "memberId=$memberId&count=$count"
        )
    }

    override suspend fun InsertProductCustomerQuestionAsync(languageId: Int, memberId: Int, model: ProductCustomerQuestionInsertModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.B2C_PRODUCT_QUESTION_BASE_URL,
            method = "InsertProductCustomerQuestionAsync",
            query = "languageId=$languageId&memberId=$memberId",
            data = model
        )
    }
}