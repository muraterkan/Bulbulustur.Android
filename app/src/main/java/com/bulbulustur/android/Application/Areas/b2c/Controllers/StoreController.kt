package com.bulbulustur.android.Application.Areas.b2c.Controllers

import androidx.lifecycle.viewModelScope
import com.bulbulustur.android.businesslayer.Core.DTO.StoreDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IStoreRepository
import com.bulbulustur.android.businesslayer.Core.Util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class StoreControllerState(
    val IsLoading: Boolean = false,
    val CurrentAction: String? = null,
    val StoreListResult: Result<List<StoreDTO>>? = null,
    val StoreDetailResult: Result<StoreDTO?>? = null,
    val ErrorMessage: String? = null
) {
    val Stores: List<StoreDTO>
        get() = StoreListResult?.Data.orEmpty()

    val StoreDetail: StoreDTO?
        get() = StoreDetailResult?.Data
}

class StoreController(
    private val storeRepository: IStoreRepository
) : BaseController() {

    private val _state = MutableStateFlow(StoreControllerState())
    val State: StateFlow<StoreControllerState> = _state.asStateFlow()

    fun LoadList() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    CurrentAction = "List",
                    ErrorMessage = null
                )
            }

            val response = storeRepository.GetStoreListAsync()

            _state.update {
                it.copy(
                    IsLoading = false,
                    CurrentAction = "List",
                    StoreListResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }

    fun LoadDetail(storeId: Int) {
        if (storeId <= 0) {
            _state.update {
                it.copy(
                    IsLoading = false,
                    CurrentAction = "Detail",
                    StoreDetailResult = null,
                    ErrorMessage = null
                )
            }

            return
        }

        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    CurrentAction = "Detail",
                    ErrorMessage = null
                )
            }

            val response = storeRepository.GetStoreByIdExtendedAsync(storeId)

            _state.update {
                it.copy(
                    IsLoading = false,
                    CurrentAction = "Detail",
                    StoreDetailResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }
}
