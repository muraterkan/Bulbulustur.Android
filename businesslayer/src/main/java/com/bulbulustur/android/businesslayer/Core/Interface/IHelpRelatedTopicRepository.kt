package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.HelpRelatedTopicDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.HelpRelatedTopicUpdateModel

interface IHelpRelatedTopicRepository {

    suspend fun GetHelpRelatedTopicListAsync(): Result<List<HelpRelatedTopicDTO>>

    suspend fun GetHelpRelatedTopicByIdAsync(
        helpRelatedTopicId: Int
    ): Result<HelpRelatedTopicUpdateModel?>

    suspend fun GetHelpRelatedTopicByIdExtendedAsync(
        helpRelatedTopicId: Int
    ): Result<HelpRelatedTopicDTO?>
}
