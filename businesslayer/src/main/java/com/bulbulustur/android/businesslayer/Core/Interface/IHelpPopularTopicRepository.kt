package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.HelpPopularTopicDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.HelpPopularTopicUpdateModel

interface IHelpPopularTopicRepository {

    suspend fun GetHelpPopularTopicListAsync(): Result<List<HelpPopularTopicDTO>>

    suspend fun GetHelpPopularTopicByIdAsync(
        helpPopularTopicId: Int
    ): Result<HelpPopularTopicUpdateModel?>

    suspend fun GetHelpPopularTopicByIdExtendedAsync(
        helpPopularTopicId: Int
    ): Result<HelpPopularTopicDTO?>
}
