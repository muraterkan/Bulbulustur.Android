package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.ProductCustomerQuestionDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductCustomerQuestionInsertModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IProductCustomerQuestionRepository {

    suspend fun GetProductCustomerQuestionsAsync(
        productId: Int,
        count: Int = 100
    ): Result<List<ProductCustomerQuestionDTO>>

    suspend fun InsertProductCustomerQuestionAsync(
        languageId: Int,
        memberId: Int,
        model: ProductCustomerQuestionInsertModel
    ): Result<Unit>
}